package com.example.swagger.common.dto.response;

import com.example.swagger.common.exception.BizException;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CommonResponseDTO<T> extends ComResultDTO {
    public CommonResponseDTO() {
    }

    public CommonResponseDTO(T body) {
        this.setBody(body);
    }

    private T body;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        // 조회결과가 null 일경우, 404 조회결과가 없습니다. 리턴
        if (HttpMethod.GET.toString().equals(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getMethod())
                && body == null) {
            throw new BizException("data_not_found");
        }

        this.body = body;
    }

}
