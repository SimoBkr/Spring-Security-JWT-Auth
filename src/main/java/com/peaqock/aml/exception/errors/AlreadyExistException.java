package com.peaqock.aml.exception.errors;

public class AlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = -7179695910349724263L;

    public AlreadyExistException(String message) {
        super(message);
    }
}
