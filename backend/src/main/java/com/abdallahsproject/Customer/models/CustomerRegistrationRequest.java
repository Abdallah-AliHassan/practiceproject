package com.abdallahsproject.Customer.models;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age
) {

}
