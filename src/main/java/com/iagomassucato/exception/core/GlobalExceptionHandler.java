package com.iagomassucato.exception.core;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionResponseFactory exceptionResponseFactory;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, Object> emptyFieldErrors = new LinkedHashMap<>();
        Map<String, Object> emailErrors = new LinkedHashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {

            String field = fieldError.getField();
            String code = fieldError.getCode();

            switch (Objects.requireNonNull(code)) {

                case "NotBlank", "NotNull" -> emptyFieldErrors.put(field, fieldError.getDefaultMessage());

                case "Email" -> {
                    if ("email".equals(field)) {
                        emailErrors.put("email", fieldError.getDefaultMessage());
                    }
                }
            }
        }

        ErrorEnum errorEnum;
        Map<String, Object> errors;

        if (!emptyFieldErrors.isEmpty()) {
            errorEnum = ErrorEnum.FIELD_INVALID;
            errors = emptyFieldErrors;
        } else if (!emailErrors.isEmpty()) {
            errorEnum = ErrorEnum.EMAIL_INVALID;
            errors = emailErrors;
        } else {
            errorEnum = ErrorEnum.FIELD_INVALID;
            errors = emptyFieldErrors;
        }

        String uri = ((ServletWebRequest) request)
                .getRequest()
                .getRequestURI();

        ProblemDetail problemDetail = exceptionResponseFactory
                .createProblemDetail(errorEnum, errors, uri);

        return ResponseEntity
                .status(errorEnum.getStatus())
                .body(problemDetail);
    }

    @ExceptionHandler(ApiException.class)
    public ProblemDetail handleApiException(
            ApiException ex,
            HttpServletRequest httpServletRequest) {

        return exceptionResponseFactory
                .createProblemDetail(
                        ex.getErrorEnum(),
                        ex.getErrors(),
                        httpServletRequest.getRequestURI());




    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            HttpServletRequest httpServletRequest) {

        ErrorEnum errorEnum = ErrorEnum.DATABASE_VIOLATION;

        return exceptionResponseFactory
                .createProblemDetail(
                        errorEnum,
                        null,
                        httpServletRequest.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException            (Exception ex,
             HttpServletRequest httpServletRequest) {

        log.error("Exception raised => {}", ex.getClass().getSimpleName());

        ErrorEnum errorEnum = ErrorEnum.GENERIC_ERROR;

        return exceptionResponseFactory
                .createProblemDetail(
                        errorEnum,
                        null,
                        httpServletRequest.getRequestURI());
    }
}
