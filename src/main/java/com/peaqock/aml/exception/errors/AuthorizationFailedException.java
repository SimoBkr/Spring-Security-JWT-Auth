package com.peaqock.aml.exception.errors;

public class AuthorizationFailedException extends RuntimeException {

    private static final long serialVersionUID = -8864020880518606156L;

    public AuthorizationFailedException(String message) {
        super(message);
    }
}
