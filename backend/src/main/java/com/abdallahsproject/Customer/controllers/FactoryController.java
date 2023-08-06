package com.abdallahsproject.Customer.controllers;

import com.abdallahsproject.Customer.models.Factory;
import com.abdallahsproject.Customer.services.FactoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/factory")
public class FactoryController {

    private final FactoryService factoryService;

    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @GetMapping
    public List<Factory> getAllFactories() {
        return factoryService.selectAllFactories();
    }

    @PostMapping
    public void postFactory(@RequestBody Factory request) {
        factoryService.addFactory(request);
    }
}
