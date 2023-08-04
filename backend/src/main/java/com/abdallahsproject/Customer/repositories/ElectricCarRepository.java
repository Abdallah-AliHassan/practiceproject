package com.abdallahsproject.Customer.repositories;

import com.abdallahsproject.Customer.models.ElectricCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ElectricCarRepository extends JpaRepository<ElectricCar, Long> {

    List<ElectricCar> findElectricCarsByEmail(String email);

    void deleteElectricCarByEmail(String email);

    Optional<ElectricCar> findElectricCarById(Long id);
}
