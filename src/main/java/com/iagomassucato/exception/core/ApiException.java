package com.iagomassucato.exception.core;

import lombok.Getter;
import java.util.Map;

@Getter
public class ApiException extends RuntimeException{
    private final ErrorEnum errorEnum;
    private final Map<String, Object> errors;

    public ApiException(ErrorEnum errorEnum, Map<String, Object> errors) {
        super(errorEnum.getDetails());
        this.errorEnum = errorEnum;
        this.errors = errors;
    }
}
