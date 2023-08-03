package com.abdallahsproject.Customer;

import com.abdallahsproject.exception.DuplicateResourceException;
import com.abdallahsproject.exception.RequestValidationException;
import com.abdallahsproject.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void canGetAllCustomers() {
        sut.getAllCustomers();

        verify(customerDao, times(1)).selectAllCustomers();
    }

    @Test
    void canGetCustomer() {
        long id = 1;
        Customer customer = new Customer(
                id, "Abdul", "Abdul@gmail.com", 19
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        Customer actual = sut.getCustomer(id);

        verify(customerDao, times(1)).selectCustomerById(id);
        assertThat(actual).isEqualTo(customer);
    }

    @Test
    void getCustomer_notFound() {
        long id = 10;

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sut.getCustomer(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("customer with id [%s] not found".formatted(id));
    }

    @Test
    void addCustomer() {
        String email = "alex@email.com";

        when(customerDao.existsPersonWithEmail(email)).thenReturn(false);

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "Alex", email, 19
        );

        sut.addCustomer(request);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao).insertCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getId()).isNull();
        assertThat(capturedCustomer.getName()).isEqualTo(request.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(request.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(request.age());
    }

    @Test
    void addCustomer_emailExists() {
        String email = "alex@email.com";

        when(customerDao.existsPersonWithEmail(email)).thenReturn(true);

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "Alex", email, 19
        );

        verify(customerDao, never()).insertCustomer(any());

        assertThatThrownBy(() -> sut.addCustomer(request))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("Email already taken");
    }

    @Test
    void deleteCustomerById() {
        long id = 1;

        when(customerDao.existsCustomerById(id)).thenReturn(true);

        sut.deleteCustomerById(id);

        verify(customerDao, times(1)).deleteCustomerById(id);
    }

    @Test
    void deleteCustomerById_customerNotExists() {
        long id = 1;

        when(customerDao.existsCustomerById(id)).thenReturn(false);

        assertThatThrownBy(() -> sut.deleteCustomerById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("customer with id [%s] not found".formatted(id));

        verify(customerDao, never()).deleteCustomerById(any());
    }

    @Test
    void updateCustomerAllProperties() {
        long id = 1;
        String email = "alexee@email.com";
        Customer customer = new Customer(
                id, "Abdul", "Abdul@gmail.com", 19
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(
                "Alex", email, 19
        );

        when(customerDao.existsPersonWithEmail(email)).thenReturn(false);

        sut.updateCustomer(id, updateRequest);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao, times(1)).updateCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getName()).isEqualTo(updateRequest.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(updateRequest.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(updateRequest.age());
    }

    @Test
    void updateCustomerNameOnly() {
        long id = 1;
        Customer customer = new Customer(
                id, "Abdul", "Abdul@gmail.com", 19
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(
                "Alex", null, null
        );

        sut.updateCustomer(id, updateRequest);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao, times(1)).updateCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getName()).isEqualTo(updateRequest.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(customer.getEmail());
        assertThat(capturedCustomer.getAge()).isEqualTo(customer.getAge());
    }

    @Test
    void updateCustomerEmailOnly() {
        long id = 1;
        String email = "alexee@email.com";
        Customer customer = new Customer(
                id, "Abdul", "Abdul@gmail.com", 19
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(
                null, email, null
        );

        when(customerDao.existsPersonWithEmail(email)).thenReturn(false);

        sut.updateCustomer(id, updateRequest);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao, times(1)).updateCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getName()).isEqualTo(customer.getName());
        assertThat(capturedCustomer.getEmail()).isEqualTo(updateRequest.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(customer.getAge());
    }

    @Test
    void updateCustomerAgeOnly() {
        long id = 1;
        Customer customer = new Customer(
                id, "Abdul", "Abdul@gmail.com", 19
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(
                null, null, 99
        );

        sut.updateCustomer(id, updateRequest);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao, times(1)).updateCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getName()).isEqualTo(customer.getName());
        assertThat(capturedCustomer.getEmail()).isEqualTo(customer.getEmail());
        assertThat(capturedCustomer.getAge()).isEqualTo(updateRequest.age());
    }

    @Test
    void updateCustomer_emailExists() {
        long id = 1;
        String email = "alexee@email.com";
        Customer customer = new Customer(
                id, "Abdul", "Abdul@gmail.com", 19
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(
                "Alex", email, 19
        );

        when(customerDao.existsPersonWithEmail(email)).thenReturn(true);

        assertThatThrownBy(() -> sut.updateCustomer(id, updateRequest))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("email already taken");

        verify(customerDao, never()).updateCustomer(any());
    }

    @Test
    void updateCustomer_noChanges() {
        long id = 1;
        String email = "alexee@email.com";
        Customer customer = new Customer(
                id, "Abdul", "Abdul@gmail.com", 19
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(
                "Abdul", "Abdul@gmail.com", 19
        );

        assertThatThrownBy(() -> sut.updateCustomer(id, updateRequest))
                .isInstanceOf(RequestValidationException.class)
                .hasMessage("no data changes found");

        verify(customerDao, never()).updateCustomer(any());
    }
}