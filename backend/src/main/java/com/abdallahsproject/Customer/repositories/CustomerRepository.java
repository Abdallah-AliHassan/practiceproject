package com.abdallahsproject.Customer.repositories;

import java.util.Optional;

import com.abdallahsproject.Customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(Long id);
    void deleteCustomerById(Long id);
    Optional<Customer> findCustomersById(Long id);
}
