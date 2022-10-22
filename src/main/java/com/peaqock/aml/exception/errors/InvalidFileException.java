package com.peaqock.aml.exception.errors;

public class InvalidFileException extends RuntimeException {

    private static final long serialVersionUID = -2253055704773968005L;

    public InvalidFileException(String message) {
        super(message);
    }
}
