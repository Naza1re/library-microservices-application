package com.example.securityservice.exception;

public class UserAuthNotFounException extends Throwable {
    public UserAuthNotFounException(String userNotRegistered) {
        super(userNotRegistered);
    }
}
