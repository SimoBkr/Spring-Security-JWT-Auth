package com.peaqock.aml.exception.errors;

public class AuthenticationFailedException extends RuntimeException {

    private static final long serialVersionUID = -657659879386345423L;

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
