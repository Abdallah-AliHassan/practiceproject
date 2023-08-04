package com.abdallahsproject.Customer.controllers;

import com.abdallahsproject.Customer.models.ElectricCar;
import com.abdallahsproject.Customer.services.ElectricCarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/car")
public class ElectricCarController {

    private final ElectricCarService electricCarService;

    public ElectricCarController(ElectricCarService electricCarService) {
        this.electricCarService = electricCarService;
    }


    @GetMapping("{email}")
    public List<ElectricCar> getCar(
            @PathVariable("email") String email) {
        return electricCarService.selectCarByEmail(email);
    }
}
