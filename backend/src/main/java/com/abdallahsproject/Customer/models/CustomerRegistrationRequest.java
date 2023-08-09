package com.abdallahsproject.Customer.models;

import jakarta.validation.constraints.*;

public record CustomerRegistrationRequest(
        @NotBlank(message = "Name must not be empty")
        String name,
        @NotBlank(message = "email must not be empty")
        @Email(message = "email must be right formatted")
        String email,
        @Min(16)
        @Max(100)
        Integer age
) {

}
