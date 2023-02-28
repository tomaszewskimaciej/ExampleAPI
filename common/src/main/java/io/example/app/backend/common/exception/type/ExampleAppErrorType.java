package io.example.app.backend.common.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExampleAppErrorType {

    EA_001("EA_001","ID is invalid", HttpStatus.BAD_REQUEST),
    EA_002("EA_002","User with that ID does not exist", HttpStatus.BAD_REQUEST),
    EA_003("EA_003","Task with that ID does not exist", HttpStatus.BAD_REQUEST),
    EA_004("EA_004","Invalid field", HttpStatus.BAD_REQUEST),
    EA_005("EA_005","Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);



    private final String value;
    private final String description;
    private final HttpStatus status;
}
