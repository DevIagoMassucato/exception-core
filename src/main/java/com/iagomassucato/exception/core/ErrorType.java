package com.iagomassucato.exception.core;

import org.springframework.http.HttpStatus;

public interface ErrorType {
    String getTitle();
    String getErrorCode();
    HttpStatus getStatus();
    String getDetails();
}
