package com.abdallahsproject.Customer.services;

import com.abdallahsproject.Customer.models.CarSpecs;
import com.abdallahsproject.Customer.repositories.CarSpecsRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarSpecsService {

    private final CarSpecsRepository carSpecsRepository;

    public CarSpecsService(CarSpecsRepository carSpecsRepository) {
        this.carSpecsRepository = carSpecsRepository;
    }

    public List<CarSpecs> selectAllCarSpecs() {
        return carSpecsRepository.findAll();
    }

    public void addCarSpec(CarSpecs request){
        carSpecsRepository.save(request);
    }

    public void deleteCarSpec(Long id){
        carSpecsRepository.deleteById(id);
    }

}
