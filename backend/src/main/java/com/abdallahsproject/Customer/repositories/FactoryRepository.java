package com.abdallahsproject.Customer.repositories;


import com.abdallahsproject.Customer.models.CarModel;
import com.abdallahsproject.Customer.models.Factory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactoryRepository extends JpaRepository<Factory, Long> {

    boolean existsFactoryByName(CarModel name);

}
