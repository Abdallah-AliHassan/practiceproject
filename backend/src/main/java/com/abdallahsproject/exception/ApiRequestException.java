package com.abdallahsproject.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ApiRequestException  extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
