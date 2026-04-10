package com.iagomassucato.exception.core;

import lombok.Getter;
import java.util.Map;

@Getter
public class ApiException extends RuntimeException{
    private final ErrorType errorType;
    private final Map<String, Object> errors;

    public ApiException(ErrorType errorType, Map<String, Object> errors) {
        super(errorType.getDetails());
        this.errorType = errorType;
        this.errors = errors;
    }
}
