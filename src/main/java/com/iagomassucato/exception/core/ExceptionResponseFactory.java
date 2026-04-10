package com.iagomassucato.exception.core;

import lombok.NoArgsConstructor;
import org.springframework.http.ProblemDetail;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Map;

@NoArgsConstructor
public class ExceptionResponseFactory {

    public ProblemDetail createProblemDetail(
            ErrorType errorType,
            Map<String, Object> errors,
            String uri) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(errorType.getStatus());

        problemDetail.setType(
                URI.create(("about:blank"))
        );
        problemDetail.setTitle(errorType.getTitle());
        problemDetail.setDetail(errorType.getDetails());
        problemDetail.setProperty("errorCode", errorType.getErrorCode());
        if (errors != null && !errors.isEmpty()) {
            problemDetail.setProperty("errors", errors);
        }
        problemDetail.setInstance(URI.create(uri));
        problemDetail.setProperty("timestamp", OffsetDateTime.now());

        return problemDetail;
    }
}
