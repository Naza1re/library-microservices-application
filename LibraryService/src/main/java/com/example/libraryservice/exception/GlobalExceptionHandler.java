package com.example.libraryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LibraryNotFoundException.class)
    public ResponseEntity<AppError> libraryNotFoundException(LibraryNotFoundException ex){
        return new ResponseEntity<>(new AppError("LIBRARY RECORD WITH THIS ID NOT FOUND", HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

}
