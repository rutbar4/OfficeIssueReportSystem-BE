package com.sourcery.oirs.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class,
                               IssueNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleNotFoundExceptions(Exception e) {
        ErrorMessage errorMessage = getErrorMessage(e, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    private ErrorMessage getErrorMessage(Exception e, HttpStatus httpStatus) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        errorMessage.setHttpStatus(httpStatus);
        errorMessage.setStackTrace(e.getStackTrace());
        errorMessage.setTimestamp(LocalDateTime.now());
        return errorMessage;
    }
}
