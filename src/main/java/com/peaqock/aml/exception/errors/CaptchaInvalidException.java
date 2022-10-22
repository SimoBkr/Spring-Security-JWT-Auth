package com.peaqock.aml.exception.errors;

public class CaptchaInvalidException extends RuntimeException {

    private static final long serialVersionUID = -4471660260263307825L;

    public CaptchaInvalidException(String message) {
        super(message);
    }
}
