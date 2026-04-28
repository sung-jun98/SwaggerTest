package com.example.swagger.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeycloakAdminClient {

    private final RestClient restClient;

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin.username}")
    private String adminUsername;

    @Value("${keycloak.admin.password}")
    private String adminPassword;

    @Value("${keycloak.client-id}")
    private String clientId;

    /**
     * master realm에서 어드민 토큰 발급
     */
    private String getAdminToken() {
        String tokenUrl = serverUrl + "/realms/master/protocol/openid-connect/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "admin-cli");
        params.add("username", adminUsername);
        params.add("password", adminPassword);

        Map<?, ?> response = restClient.post()
                .uri(tokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(params)
                .retrieve()
                .body(Map.class);

        return (String) response.get("access_token");
    }

    /**
     * Keycloak에 유저 생성 후 생성된 Keycloak user ID 반환
     * 생성 성공 시 Location 헤더에서 ID 추출
     */
    public String createUser(String username, String password) {
        String adminToken = getAdminToken();
        String createUserUrl = serverUrl + "/admin/realms/" + realm + "/users";

        Map<String, Object> userPayload = Map.of(
                "username", username,
                "enabled", true,
                "credentials", List.of(Map.of(
                        "type", "password",
                        "value", password,
                        "temporary", false
                ))
        );

        URI location = restClient.post()
                .uri(createUserUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + adminToken)
                .body(userPayload)
                .retrieve()
                .toBodilessEntity()
                .getHeaders()
                .getLocation();

        // Keycloak은 생성 성공 시 201 응답과 함께 Location 헤더에 유저 URL을 반환
        // 예: http://localhost:8080/admin/realms/ixi-stt-tts/users/{keycloakId}
        String locationStr = location.toString();
        return locationStr.substring(locationStr.lastIndexOf('/') + 1);
    }

    /**
     * Keycloak 유저 삭제 (DB 저장 실패 시 보상 트랜잭션용)
     * 구현만 해놓음. 아직 테스트 X (2026.04.28)
     */
    public void deleteUser(String keycloakUserId) {
        String adminToken = getAdminToken();
        String deleteUrl = serverUrl + "/admin/realms/" + realm + "/users/" + keycloakUserId;

        restClient.delete()
                .uri(deleteUrl)
                .header("Authorization", "Bearer " + adminToken)
                .retrieve()
                .toBodilessEntity();

        log.info("Keycloak 유저 롤백 완료 - keycloakId: {}", keycloakUserId);
    }
}
