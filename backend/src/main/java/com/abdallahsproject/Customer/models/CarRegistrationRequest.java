package com.abdallahsproject.Customer.models;

public record CarRegistrationRequest(
        String email,
        CarModel model
) {
}
