package com.abdallahsproject.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService sut;

    @Mock
    private CustomerDao customerDao;

    @BeforeEach
    void setUp() {
        sut = new CustomerService(customerDao);
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void getCustomer() {
    }

    @Test
    void addCustomer() {
    }

    @Test
    void deleteCustomerById() {
    }

    @Test
    void updateCustomer() {
    }
}