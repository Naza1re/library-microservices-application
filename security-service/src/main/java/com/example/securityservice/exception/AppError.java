package com.example.securityservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AppError {
    private HttpStatus httpStatus;
    private String message;

    public AppError(HttpStatus httpStatus,String message){
        this.httpStatus=httpStatus;
        this.message=message;
    }
}
