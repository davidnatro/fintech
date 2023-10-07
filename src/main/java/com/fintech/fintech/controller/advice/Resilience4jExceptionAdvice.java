package com.fintech.fintech.controller.advice;

import com.fintech.fintech.data.model.ExceptionModel;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Resilience4jExceptionAdvice {

    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    @ExceptionHandler(RequestNotPermitted.class)
    public ExceptionModel handleRequestNotPermitted(RequestNotPermitted exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.TOO_MANY_REQUESTS.value());
    }
}
