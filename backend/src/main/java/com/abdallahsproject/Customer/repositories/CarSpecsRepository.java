package com.abdallahsproject.Customer.repositories;

import com.abdallahsproject.Customer.models.CarSpecs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarSpecsRepository extends JpaRepository<CarSpecs, Long> {

}
