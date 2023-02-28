package io.example.app.backend.rest.exception.utility;

import io.example.app.backend.common.exception.type.ExampleAppErrorType;
import io.example.app.backend.rest.exception.model.ExampleAppError;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Helper {

    private static final String PATTERN_FORMAT = "dd-MM-yyyy HH:mm:SS";

    public static String formatTime(Instant time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
        return formatter.format(time);
    }

    public static ExampleAppError mapToExampleAppError(ExampleAppErrorType errorType, WebRequest request) {
        ServletWebRequest webRequest = (ServletWebRequest) request;

        return ExampleAppError.builder()
                .code(errorType.getValue())
                .path(webRequest.getRequest().getRequestURI())
                .method(webRequest.getHttpMethod())
                .status(errorType.getStatus())
                .time(formatTime(Instant.now()))
                .build();
    }
}
