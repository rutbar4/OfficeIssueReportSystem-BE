package com.sourcery.oirs.controller;

import com.sourcery.oirs.exceptions.BusyIssueNameException;
import com.sourcery.oirs.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IssueExceptionAdvice {

    @ExceptionHandler(BusyIssueNameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleBusyIssueException(){
        return new ExceptionResponse("Issue with this short summary already exist", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException (){
        return new ExceptionResponse("Issue report failed. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
