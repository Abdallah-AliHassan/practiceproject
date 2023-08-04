package com.abdallahsproject.Customer.models;

import java.util.List;

public record CustomerCarDTO(
        String name,

        String email,

        List<CarModel> model
 ) {

}
