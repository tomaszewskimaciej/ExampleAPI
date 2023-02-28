package io.example.app.backend.rest.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExampleAppError {
    private String code;
    private String path;
    private HttpMethod method;
    private String time;
    private HttpStatus status;
}
