package com.abdallahsproject.Customer.models;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {

}
