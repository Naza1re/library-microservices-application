package com.example.bookservice.exception;

public class BookNotFoundException extends Throwable {
    public BookNotFoundException(String s) {
        super(s);
    }
}
