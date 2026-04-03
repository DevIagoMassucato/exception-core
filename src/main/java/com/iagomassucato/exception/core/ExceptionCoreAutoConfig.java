package com.iagomassucato.exception.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GlobalExceptionHandler.class)
public class ExceptionCoreAutoConfig {

    @Bean
    public ExceptionResponseFactory exceptionResponseFactory() {
        return new ExceptionResponseFactory();
    }

}
