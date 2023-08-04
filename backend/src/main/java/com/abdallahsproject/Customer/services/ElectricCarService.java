package com.abdallahsproject.Customer.services;


import com.abdallahsproject.Customer.models.ElectricCar;
import com.abdallahsproject.Customer.repositories.ElectricCarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectricCarService {

    private final ElectricCarRepository electricCarRepository;

    public ElectricCarService(ElectricCarRepository electricCarRepository) {
        this.electricCarRepository = electricCarRepository;
    }

    public List<ElectricCar> selectCarByEmail(String email) {
        return electricCarRepository.findElectricCarsByEmail(email);
    }

    public void deleteCarByEmail(String email) {
        electricCarRepository.deleteElectricCarByEmail(email);
    }

    public Optional<ElectricCar> selectCarById(Long id){
        return electricCarRepository.findElectricCarById(id);
    }

}
