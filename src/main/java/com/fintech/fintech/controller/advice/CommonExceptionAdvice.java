package com.fintech.fintech.controller.advice;

import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.data.model.ExceptionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionModel handleAlreadyExistsException(IllegalArgumentException exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
