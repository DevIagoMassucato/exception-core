package com.iagomassucato.exception.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    FIELD_INVALID(
            "Field invalid",
            "VAL_001",
            HttpStatus.BAD_REQUEST,
            "One or more fields are invalid"

    ),

    EMAIL_INVALID(
            "Email invalid",
            "VAL_002",
            HttpStatus.BAD_REQUEST,
            "Invalid email address."

    ),

    DATABASE_VIOLATION(
            "Database violation",
            "DB_409",
            HttpStatus.CONFLICT,
            "Database rule violated"
    ),

    NOT_FOUND(
            "Not found",
            "RES_001",
            HttpStatus.NOT_FOUND,
            "Resource not found"
    ),

    GENERIC_ERROR(
            "Generic error",
            "SYS_500",
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Unexpected server error"
    );

    private final String title;
    private final String errorCode;
    private final HttpStatus status;
    private final String details;
}
