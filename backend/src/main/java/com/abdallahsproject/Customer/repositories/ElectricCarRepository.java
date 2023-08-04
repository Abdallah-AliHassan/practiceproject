package com.abdallahsproject.Customer.repositories;

import com.abdallahsproject.Customer.models.ElectricCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ElectricCarRepository extends JpaRepository<ElectricCar, Long> {

    List<ElectricCar> findElectricCarByEmail(String email);
}
