package com.abdallahsproject.Customer.repositories;

import com.abdallahsproject.Customer.models.ElectricCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ElectricCarRepository extends JpaRepository<ElectricCar, Long> {


    // plan jdbc is written pure sql
//    @Query(value = "", nativeQuery = true)
    List<ElectricCar> findElectricCarsByEmail(String email);

    void deleteElectricCarByEmail(String email);

    Optional<ElectricCar> findElectricCarById(Long id);
}
