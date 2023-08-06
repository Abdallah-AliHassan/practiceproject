package com.abdallahsproject.Customer.controllers;

import com.abdallahsproject.Customer.models.CarSpecs;
import com.abdallahsproject.Customer.repositories.CarSpecsRepository;
import com.abdallahsproject.Customer.services.CarSpecsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carspecs")
public class CarSpecsController {

    private final CarSpecsService carSpecsService;

    public CarSpecsController(CarSpecsService carSpecsService) {
        this.carSpecsService = carSpecsService;
    }

    @GetMapping
    public List<CarSpecs> getAllCarSpecs() {
        return carSpecsService.selectAllCarSpecs();
    }

    @PostMapping
    public void postCarSpecs(@RequestBody CarSpecs request) {
        carSpecsService.addCarSpec(request);
    }

}
