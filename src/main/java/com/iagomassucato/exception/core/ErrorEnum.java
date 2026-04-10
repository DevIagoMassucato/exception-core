package com.iagomassucato.exception.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum implements ErrorType{
    INVALID_FIELD(
            "invalid field",
            "VAL_001",
            HttpStatus.BAD_REQUEST,
            "one or more fields are invalid"

    ),

    INVALID_EMAIL(
            "invalid email",
            "VAL_002",
            HttpStatus.BAD_REQUEST,
            "invalid email address"

    ),

    DATA_INTEGRITY_VIOLATION(
            "constraint violation",
            "DB_409",
            HttpStatus.CONFLICT,
            "a database constraint was violated"
    ),

    NOT_FOUND(
            "not found",
            "RES_001",
            HttpStatus.NOT_FOUND,
            "resource not found"
    ),

    GENERIC_ERROR(
            "generic error",
            "SYS_500",
            HttpStatus.INTERNAL_SERVER_ERROR,
            "unexpected server error"
    );

    private final String title;
    private final String errorCode;
    private final HttpStatus status;
    private final String details;
}
