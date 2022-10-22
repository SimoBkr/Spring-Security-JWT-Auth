package com.peaqock.aml.exception.errors;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2857502328910691771L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
