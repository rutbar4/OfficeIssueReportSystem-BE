package com.sourcery.oirs.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class ExceptionResponse {
    private final String message;
    private final int status;
    private final long timestamp;


    public ExceptionResponse(String message, HttpStatus status) {
        this(message, status, null);
    }

    public ExceptionResponse(String message, HttpStatus status, String reason) {
        this.message = message;
        this.status = status.value();

        timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
    }
}
