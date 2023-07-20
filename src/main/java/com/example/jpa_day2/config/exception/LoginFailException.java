package com.example.jpa_day2.config.exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException(String message) {
        super(message);
    }
}
