package io.example.app.backend.rest.exception.handler;

import io.example.app.backend.common.exception.ExampleAppException;
import io.example.app.backend.common.exception.type.ExampleAppErrorType;
import io.example.app.backend.rest.exception.model.ExampleAppError;
import io.example.app.backend.rest.exception.utility.Helper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice()
public class ExampleAppExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExampleAppError error = Helper.mapToExampleAppError(ExampleAppErrorType.EA_004, request);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), error.getStatus(), request);
    }

    @ExceptionHandler(value = {ExampleAppException.class})
    protected ResponseEntity<Object> handleFakeBankException(ExampleAppException exception, WebRequest request) {
        ExampleAppError error = Helper.mapToExampleAppError(exception.getErrorType(), request);
        return handleExceptionInternal(exception, error, new HttpHeaders(), error.getStatus(), request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGenericException(Exception exception, WebRequest request) {
        ExampleAppError error = Helper.mapToExampleAppError(ExampleAppErrorType.EA_005, request);
        return handleExceptionInternal(exception, error, new HttpHeaders(), error.getStatus(), request);
    }
}