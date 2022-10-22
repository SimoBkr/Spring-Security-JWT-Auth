package com.peaqock.aml.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiErrorMessage {

    INTERNAL_SERVER_ERROR("Internal server error,please try later"),
    USER_ALREADY_EXISTS("User is already exist"),
    NO_FILE_FOUND("Could not find this file"),
    NO_USER_FOUND("Could not find this user"),
    NO_RECORD_FOUND("Could not find this record"),
    AUTHORIZATION_FAILED("Authorization failed. no sufficient privileges"),
    BAD_CREDENTIALS("Authentication failed due to invalid authentication credentials"),
    AUTHENTICATION_FAILED("Authentication failed due to invalid authentication credentials or a missing Authorization header"),
    INVALID_DATABASE("Invalid database"),
    INVALID_FILE("Invalid file to upload"),
    INVALID_CAPTCHA("Invalid CAPTCHA"),
    INVALID_EMAIL_VERIFICATION("Invalid email verification"),
    EMAIL_VERIFICATION("Email verification is already in {}: "),
    INVALID_RESET_PASSWORD_VERIFICATION("Invalid reset password verification"),
    INVALID_JOIN_OPERATION("Invalid join operation"),
    INVALID_TEMPLATE_FORMAT("Invalid template format"),
    ACCESS_DENIED_EXCEPTION("Access denied,you dont have access to this resource");

    private final String errorMessage;
}
