package com.peaqock.aml.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiValidationError {

    private String field;

    private String object;

    private String message;

    private Object rejectedValue;
}
