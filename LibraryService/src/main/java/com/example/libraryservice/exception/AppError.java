package com.example.libraryservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
@Getter
@Setter
public class AppError {

    private HttpStatus httpStatus;
    private String message;

    public AppError(String message,HttpStatus httpStatus){
        this.message=message;
        this.httpStatus=httpStatus;
    }
}
