package com.fintech.fintech.controller.advice;

import com.fintech.fintech.data.model.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class CommonExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public ExceptionModel handleHttpClientError(HttpClientErrorException exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.class)
    public ExceptionModel handleHttpServerError(HttpServerErrorException exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionModel handleAlreadyExistsException(IllegalArgumentException exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
