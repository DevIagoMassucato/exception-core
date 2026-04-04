package com.iagomassucato.exception.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    FIELD_INVALID(
            "field invalid",
            "VAL_001",
            HttpStatus.BAD_REQUEST,
            "one or more fields are invalid"

    ),

    EMAIL_INVALID(
            "email invalid",
            "VAL_002",
            HttpStatus.BAD_REQUEST,
            "invalid email address."

    ),

    DATABASE_VIOLATION(
            "database violation",
            "DB_409",
            HttpStatus.CONFLICT,
            "database rule violated"
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
