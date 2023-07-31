package com.abdallahsproject.Customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {

}
