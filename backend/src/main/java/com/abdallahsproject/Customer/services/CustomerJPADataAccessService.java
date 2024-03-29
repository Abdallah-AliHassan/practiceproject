package com.abdallahsproject.Customer.services;

import java.util.List;
import java.util.Optional;

import com.abdallahsproject.Customer.models.Customer;
import com.abdallahsproject.Customer.repositories.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository("jpa")
public class CustomerJPADataAccessService implements CustomerDao {

    private final CustomerRepository customerRepository;

    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> selectCustomerById(Long id) {
        return customerRepository.findCustomersById(id);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }

    @Override
    public boolean existsCustomerById(Long customerId) {
        return customerRepository.existsCustomerById(customerId);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteCustomerById(customerId);
    }

    @Override
    public void updateCustomer(Customer update) {
        customerRepository.save(update);
    }

    @Override
    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
