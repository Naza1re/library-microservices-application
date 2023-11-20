package com.example.securityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAuthNotFoundException.class)
    public ResponseEntity<AppError> userAuthNotFoundException(){
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND,"USER NOT AUTHORIZED"),HttpStatus.NOT_FOUND);
    }
}
