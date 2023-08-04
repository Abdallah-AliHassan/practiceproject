package com.abdallahsproject.Customer.services;


import com.abdallahsproject.Customer.models.CarRegistrationRequest;
import com.abdallahsproject.Customer.models.Customer;
import com.abdallahsproject.Customer.models.CustomerCarDTO;
import com.abdallahsproject.Customer.models.ElectricCar;
import com.abdallahsproject.Customer.repositories.ElectricCarRepository;
import com.abdallahsproject.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ElectricCarService {

    private final ElectricCarRepository electricCarRepository;
    private final CustomerService customerService;

    public ElectricCarService(ElectricCarRepository electricCarRepository, CustomerService customerService) {
        this.electricCarRepository = electricCarRepository;
        this.customerService = customerService;

    }

    public List<ElectricCar> selectCarByEmail(String email) {
        return electricCarRepository.findElectricCarsByEmail(email);
    }

    public void deleteCarByEmail(String email) {
        electricCarRepository.deleteElectricCarByEmail(email);
    }

    public ElectricCar selectCarById(Long id){
        return electricCarRepository.findElectricCarById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "customer with id [%s] not found".formatted(id)
                ));
    }

    public List<ElectricCar> getAllCars() {
        return electricCarRepository.findAll();
    }

    public CustomerCarDTO getCustomerInfo(Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        List<ElectricCar> electricCars = selectCarByEmail(customer.getEmail());

        return  new CustomerCarDTO(
                        customer.getName(),
                        customer.getEmail(),
                        electricCars
                                .stream()
                                .map(ElectricCar::getModel)
                                .collect(Collectors.toList())
                );
}

    public void addElectricCar(CarRegistrationRequest carRegistrationRequest) {
        ElectricCar electricCar = new ElectricCar(
                carRegistrationRequest.email(),
                carRegistrationRequest.model()
        );
        electricCarRepository.save(electricCar);
    }
}
