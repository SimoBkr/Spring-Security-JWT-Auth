package com.peaqock.aml.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ApiError {

    private HttpStatus status;

    private String message;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    @Builder.Default
    private List<ApiValidationError> errors = List.of();

    public List<ApiValidationError> generateValidationError(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(fieldError ->
                        ApiValidationError.builder()
                                .field(fieldError.getField())
                                .object(fieldError.getObjectName())
                                .message(fieldError.getDefaultMessage())
                                .rejectedValue(fieldError.getRejectedValue())
                                .build()
                ).collect(Collectors.toList());
    }
}
