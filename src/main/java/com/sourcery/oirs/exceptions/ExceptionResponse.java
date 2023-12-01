package com.sourcery.oirs.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {
    private final String message;
    private final int status;

    public ExceptionResponse(String message, HttpStatus status) {
        this(message, status, null);
    }

    public ExceptionResponse(String message, HttpStatus status, String reason) {
        this.message = message;
        this.status = status.value();
    }
}
