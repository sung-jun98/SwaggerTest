package com.example.swagger.common.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonResponseEntity<T> extends ResponseEntity<CommonResponseDTO<T>> {
    public CommonResponseEntity(CommonResponseDTO<T> body, HttpStatus status) {
        super(body, status);
        body.setHttpStatusCode(status.value());
    }

    public CommonResponseEntity(HttpStatus unauthorized) {
        super(unauthorized);
    }

}
