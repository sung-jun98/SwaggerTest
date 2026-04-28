package com.example.swagger.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String usrId;           // USR_ID (PK, "USR-{UUID}")
    private String keycloakId;      // KEYCLOAK_ID
    private String loginNm;         // LOGIN_NM
    private String usrNm;           // USR_NM
    private String usrTypeCd;       // USR_TYPE_CD
    private String useYn;           // USE_YN
    private String crteUsrId;       // CRTE_USR_ID
    private LocalDateTime crteDttm; // CRTE_DTTM
    private String updUsrId;        // UPD_USR_ID
    private LocalDateTime updDttm;  // UPD_DTTM
}
