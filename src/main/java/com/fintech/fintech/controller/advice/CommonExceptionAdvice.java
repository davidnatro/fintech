package com.fintech.fintech.controller.advice;

import com.fintech.fintech.data.model.ExceptionModel;
import com.fintech.fintech.exception.AlreadyExistsException;
import com.fintech.fintech.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionModel handleNotFoundException(NotFoundException exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    public ExceptionModel handleAlreadyExistsException(AlreadyExistsException exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.CONFLICT.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionModel handleAlreadyExistsException(IllegalArgumentException exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
