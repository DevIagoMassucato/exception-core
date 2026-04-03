package com.iagomassucato.exception.core;

import lombok.NoArgsConstructor;
import org.springframework.http.ProblemDetail;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Map;

@NoArgsConstructor
public class ExceptionResponseFactory {

    public ProblemDetail createProblemDetail(
            ErrorEnum errorEnum,
            Map<String, Object> errors,
            String uri) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(errorEnum.getStatus());

        problemDetail.setType(
                URI.create(("about:blank"))
        );
        problemDetail.setTitle(errorEnum.getTitle());
        problemDetail.setDetail(errorEnum.getDetails());
        problemDetail.setProperty("errorCode", errorEnum.getErrorCode());
        if (errors != null && !errors.isEmpty()) {
            problemDetail.setProperty("errors", errors);
        }
        problemDetail.setInstance(URI.create(uri));
        problemDetail.setProperty("timestamp", OffsetDateTime.now());

        return problemDetail;
    }
}
