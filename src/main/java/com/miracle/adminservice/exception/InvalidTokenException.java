package com.miracle.adminservice.exception;

import com.sun.jdi.request.InvalidRequestStateException;

public class InvalidTokenException extends InvalidRequestStateException {

    public InvalidTokenException(String msg) {
        super(msg);
    }
}
