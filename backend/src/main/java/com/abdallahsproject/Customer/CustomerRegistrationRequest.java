package com.abdallahsproject.Customer;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age
) {

}
