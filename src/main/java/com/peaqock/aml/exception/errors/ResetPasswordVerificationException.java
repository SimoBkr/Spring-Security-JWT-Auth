package com.peaqock.aml.exception.errors;

public class ResetPasswordVerificationException extends RuntimeException {

    private static final long serialVersionUID = -1266835411205974321L;

    public ResetPasswordVerificationException(String message) {
        super(message);
    }
}
