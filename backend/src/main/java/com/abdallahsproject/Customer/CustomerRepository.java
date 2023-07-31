package com.abdallahsproject.Customer;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(Long id);
    void deleteCustomerById(Long id);
    Optional<Customer> findCustomersById(Long id);
}
