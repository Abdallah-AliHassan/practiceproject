package com.abdallahsproject.Customer.repositories;

import java.util.Optional;

import com.abdallahsproject.Customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(Long id);
    void deleteById(Long id);
    Optional<Customer> findCustomerById(Long id);

    Customer findCustomerByEmail(String email);
}

