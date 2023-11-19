package com.example.securityservice.exception;

public class UserAuthNotFoundException extends Throwable {
    public UserAuthNotFoundException(String userNotRegistered) {
        super(userNotRegistered);
    }
}
