package com.example.swagger.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "회원가입 응답 DTO")
public class CreateUserResponseDTO {

    @Schema(description = "앱 DB 사용자 ID", example = "USR-550e8400-e29b-41d4-a716-446655440000")
    private String usrId;

    @Schema(description = "Keycloak 사용자 ID", example = "a1b2c3d4-...")
    private String keycloakId;

    @Schema(description = "로그인 아이디", example = "hong_gildong")
    private String loginNm;

    @Schema(description = "사용자 이름", example = "홍길동")
    private String usrNm;

    @Schema(description = "사용자 유형 코드", example = "USER")
    private String usrTypeCd;
}
