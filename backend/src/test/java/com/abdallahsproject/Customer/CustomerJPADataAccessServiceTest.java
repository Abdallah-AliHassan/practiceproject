package com.abdallahsproject.Customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CustomerJPADataAccessServiceTest {

    private CustomerJPADataAccessService sut;
    private AutoCloseable autoCloseable;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        sut = new CustomerJPADataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void selectAllCustomers() {
        sut.selectAllCustomers();
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void selectCustomerById() {
        long id = 1;

        sut.selectCustomerById(id);

        verify(customerRepository, times(1)).findCustomersById(id);
    }

    @Test
    void existsPersonWithEmail() {
        String email = "test@email.com";

        sut.existsPersonWithEmail(email);

        verify(customerRepository, times(1)).existsCustomerByEmail(email);
    }

    @Test
    void existsCustomerById() {
        long id = 1;

        sut.existsCustomerById(id);

        verify(customerRepository, times(1)).existsCustomerById(id);
    }

    @Test
    void deleteCustomerById() {
        long id = 1;

        sut.deleteCustomerById(id);

        verify(customerRepository, times(1)).deleteCustomerById(id);
    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer(
                1L, "Ali", "Ali@gmail.com", 20,
                Gender.MALE);

        sut.updateCustomer(customer);

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void insertCustomer() {
        Customer customer = new Customer(
                1L, "Ali", "Ali@gmail.com", 20,
                Gender.MALE);

        sut.insertCustomer(customer);

        verify(customerRepository, times(1)).save(customer);
    }
}