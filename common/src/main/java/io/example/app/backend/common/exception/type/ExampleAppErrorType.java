package io.example.app.backend.common.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExampleAppErrorType {

    EA_001("EA_001","ID is null", HttpStatus.BAD_REQUEST),
    EA_002("EA_002","Wrong data", HttpStatus.BAD_REQUEST),
    EA_003("EA_003","", HttpStatus.BAD_REQUEST),
    EA_004("EA_004","User with that ID does not exist", HttpStatus.BAD_REQUEST),
    EA_005("EA_005","Task with that ID does not exist", HttpStatus.BAD_REQUEST);



    private final String value;
    private final String description;
    private final HttpStatus status;
}
