package com.example.bookservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class AppError {
    private HttpStatus httpStatus;
    private String message;

    public AppError(String message, HttpStatus httpStatus) {
        this.httpStatus=httpStatus;
        this.message=message;
    }
}
