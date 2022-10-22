package com.peaqock.aml.exception;

import com.peaqock.aml.exception.errors.AuthenticationFailedException;
import com.peaqock.aml.exception.errors.AuthorizationFailedException;
import com.peaqock.aml.exception.errors.NoRecordFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var apiError = ApiError.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .message("Validation error. Check 'errors' field for details.")
                .build();
        apiError.setErrors(apiError.generateValidationError(ex.getBindingResult().getFieldErrors()));
        return ResponseEntity.unprocessableEntity().body(apiError);
    }

    @Override
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildErrorResponse("Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object> handleAuthenticationFailedException(AuthenticationFailedException ex) {
        return buildErrorResponse(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthorizationFailedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<Object> handleAuthorizationFailedException(AuthorizationFailedException ex) {
        return buildErrorResponse(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNoRecordFoundException(NoRecordFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus status) {
        return buildErrorResponse(exception.getMessage(), status);
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(
                ApiError.builder()
                        .status(status)
                        .message(message)
                        .build()
        );
    }
}
