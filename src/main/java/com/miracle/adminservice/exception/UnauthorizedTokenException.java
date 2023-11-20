package com.miracle.adminservice.exception;

public class UnauthorizedTokenException extends RuntimeException{
    public UnauthorizedTokenException(String message) {
        super(message);
    }
}
