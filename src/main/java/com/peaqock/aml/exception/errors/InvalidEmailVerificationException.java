package com.peaqock.aml.exception.errors;

public class InvalidEmailVerificationException extends RuntimeException {

    private static final long serialVersionUID = 5184510245132427573L;

    public InvalidEmailVerificationException(String message) {
        super(message);
    }

}
