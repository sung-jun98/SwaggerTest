package com.example.swagger.api.service;

import com.example.swagger.api.dao.UserDAO;
import com.example.swagger.api.dto.UserDTO;
import com.example.swagger.api.dto.request.CreateUserRequestDTO;
import com.example.swagger.api.dto.response.CreateUserResponseDTO;
import com.example.swagger.api.service.impl.UserService;
import com.example.swagger.config.KeycloakAdminClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;
    private final KeycloakAdminClient keycloakAdminClient;

    /**
     * 회원가입
     * 1. Keycloak에 유저 생성 (Admin API)
     * 2. TB_USR에 INSERT
     * 3. DB 실패 시 Keycloak 유저 삭제 (보상 트랜잭션)
     */
    @Override
    @Transactional
    public CreateUserResponseDTO signUp(CreateUserRequestDTO request) {
        // 1. Keycloak에 유저 생성 → keycloakId 반환
        String keycloakId = keycloakAdminClient.createUser(request.getLoginNm(), request.getPassword());
        log.info("Keycloak 유저 생성 완료 - loginNm: {}, keycloakId: {}", request.getLoginNm(), keycloakId);

        // 2. TB_USR INSERT
        String usrId = String.valueOf(UUID.randomUUID());
        UserDTO userDTO = UserDTO.builder()
                .usrId(usrId)
                .keycloakId(keycloakId)
                .loginNm(request.getLoginNm())
                .usrNm(request.getUsrNm())
                .usrTypeCd(request.getUsrTypeCd())
                .useYn("Y")
                .build();

        try {
            userDao.insertUser(userDTO);
        } catch (Exception e) {
            // DB 저장 실패 시 Keycloak 유저 삭제 (보상 트랜잭션)
            log.error("TB_USR INSERT 실패. Keycloak 유저 롤백 시작 - keycloakId: {}", keycloakId);
            keycloakAdminClient.deleteUser(keycloakId);
            throw new RuntimeException("회원가입 처리 중 오류가 발생했습니다.", e);
        }

        return CreateUserResponseDTO.builder()
                .usrId(usrId)
                .keycloakId(keycloakId)
                .loginNm(request.getLoginNm())
                .usrNm(request.getUsrNm())
                .usrTypeCd(request.getUsrTypeCd())
                .build();
    }


}
