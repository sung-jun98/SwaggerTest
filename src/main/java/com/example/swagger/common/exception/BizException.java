package com.example.swagger.common.exception;

import com.example.swagger.common.dto.response.ComResultDTO;

import java.util.List;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String ymlKey;
    private List<String> arrayReplace;
    private Throwable throwable = null;
    private ComResultDTO resultDto;

    public BizException(ComResultDTO resultDto) {
        this.resultDto = resultDto;
    }

    public BizException(Throwable throwable, ComResultDTO resultDto) {
        this.throwable = throwable;
        this.resultDto = resultDto;
    }

    public BizException(String ymlKey) {
        this.ymlKey = ymlKey;
    }

    public BizException(Throwable throwable, String ymlKey) {
        this.throwable = throwable;
        this.ymlKey = ymlKey;
    }

    public BizException(String ymlKey, List<String> arrayReplace) {
        this.ymlKey = ymlKey;
        this.arrayReplace = arrayReplace;
    }

    public BizException(Throwable throwable, String ymlKey, List<String> arrayReplace) {
        this.throwable = throwable;
        this.ymlKey = ymlKey;
        this.arrayReplace = arrayReplace;
    }

    public BizException(String code, String message, String status) {
        this.resultDto = new ComResultDTO();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        resultDto.setStatus(status);
    }

    public BizException(Throwable throwable, String code, String message, String status) {
        this.throwable = throwable;
        this.resultDto = new ComResultDTO();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        resultDto.setStatus(status);
    }

    public String getYmlKey() {
        return ymlKey;
    }

    public void setYmlKey(String ymlKey) {
        this.ymlKey = ymlKey;
    }

    public List<String> getArrayReplace() {
        return arrayReplace;
    }

    public void setArrayReplace(List<String> arrayReplace) {
        this.arrayReplace = arrayReplace;
    }

    public ComResultDTO getResultDto() {
        return resultDto;
    }

    public void setResultDto(ComResultDTO resultDto) {
        this.resultDto = resultDto;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
