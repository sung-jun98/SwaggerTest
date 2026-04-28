package com.example.swagger.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원가입 요청 DTO")
public class CreateUserRequestDTO {

    @Schema(description = "로그인 아이디", example = "hong_gildong")
    private String loginNm;

    @Schema(description = "비밀번호", example = "password123!")
    private String password;

    @Schema(description = "사용자 이름", example = "홍길동")
    private String usrNm;

    @Schema(description = "사용자 유형 코드", example = "USER")
    private String usrTypeCd;
}
