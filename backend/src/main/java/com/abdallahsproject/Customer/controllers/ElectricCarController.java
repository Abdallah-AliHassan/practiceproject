package com.abdallahsproject.Customer.controllers;

import com.abdallahsproject.Customer.models.ElectricCar;
import com.abdallahsproject.Customer.services.ElectricCarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("{email}")
    public void deleteCustomer(
            @PathVariable("email") String email) {
        electricCarService.deleteCarByEmail(email);
    }

    @GetMapping("{id}")
    public Optional<ElectricCar> getCar(
            @PathVariable("id") Long id) {
        return electricCarService.selectCarById(id);
    }
}
