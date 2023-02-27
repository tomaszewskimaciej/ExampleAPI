package io.example.app.backend.common.exception;

import io.example.app.backend.common.exception.type.ExampleAppErrorType;
import lombok.Getter;

@Getter
public class ExampleAppException extends RuntimeException {

    private ExampleAppErrorType errorType;

    public ExampleAppException(ExampleAppErrorType errorType) {
        super(errorType.getValue());
        this.errorType = errorType;
    }
}
