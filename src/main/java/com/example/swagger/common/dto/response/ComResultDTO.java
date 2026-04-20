package com.example.swagger.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ComResultDTO {
    private int httpStatusCode = HttpStatus.OK.value();
    private String code = "20000000";
    private String message = "정상 처리 되었습니다.";
    private String detailMessage = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status = null;
    @JsonIgnore
    private HttpHeaders httpHeaders;

}
