package com.peaqock.aml.exception.errors;

public class NoRecordFoundException extends RuntimeException {

    private static final long serialVersionUID = 7576017737985396193L;

    public NoRecordFoundException(String message) {
        super(message);
    }
}
