package com.example.bookservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<AppError> bookNotFoundException(BookNotFoundException e) {
        return new ResponseEntity<>(new AppError("BOOK WITH THIS ID IS NOT FOUND", HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

}
