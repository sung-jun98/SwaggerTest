package com.example.swagger.api.controller;

import com.example.swagger.api.dto.request.CreateUserRequestDTO;
import com.example.swagger.api.dto.response.CreateUserResponseDTO;
import com.example.swagger.api.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AUTH", description = "인증 관련 API (회원가입, 로그인)")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /**
     * 회원가입
     * - Keycloak에 유저 생성
     * - TB_USR에 INSERT
     */
    @Operation(summary = "회원가입", description = "Keycloak 유저 생성 후 TB_USR에도 저장한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 아이디")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<CreateUserResponseDTO> signUp(@RequestBody CreateUserRequestDTO request) {
        CreateUserResponseDTO response = userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
