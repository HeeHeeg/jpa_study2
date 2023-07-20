package com.example.jpa_day2.config.exception;

public class DuplicationException extends RuntimeException{
    public DuplicationException(String message) {
        super(message);
    }
}
