package com.abdallahsproject.Customer.controllers;

import com.abdallahsproject.Customer.models.ElectricCarRegistrationRequest;
import com.abdallahsproject.Customer.models.CustomerCarDTO;
import com.abdallahsproject.Customer.models.ElectricCar;
import com.abdallahsproject.Customer.services.ElectricCarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/car")
public class ElectricCarController {

    private final ElectricCarService electricCarService;

    public ElectricCarController(ElectricCarService electricCarService) {
        this.electricCarService = electricCarService;
    }

    @GetMapping("{email}")
    public List<ElectricCar> getCarByEmail(
            @PathVariable("email") String email) {
        return electricCarService.selectCarByEmail(email);
    }

    @DeleteMapping("{email}")
    public void deleteCarByEmail(
            @PathVariable("email") String email) {
        electricCarService.deleteCarByEmail(email);
    }

    @GetMapping("find/{id}")
    public ElectricCar getCarById(
            @PathVariable("id") Long id) {
        return electricCarService.selectCarById(id);
    }

    @GetMapping
    public List<ElectricCar> getAllElectricCars() {
        return electricCarService.getAllCars();
    }

    @PostMapping
    public void registerElectricCar(@RequestBody ElectricCarRegistrationRequest request){
        electricCarService.addElectricCar(request);
    }

    @GetMapping("info/{id}")
    public CustomerCarDTO getCustomerInfo(
            @PathVariable("id") Long customerId) {
        return electricCarService.getCustomerInfo(customerId);
    }
}


