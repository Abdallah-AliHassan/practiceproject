package com.abdallahsproject.Customer.repositories;

import com.abdallahsproject.AbstractTestcontainers;
import com.abdallahsproject.Customer.models.Customer;
import com.abdallahsproject.Customer.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest extends AbstractTestcontainers {

    @Autowired
    private CustomerRepository rut;

    @BeforeEach
    void setUp() {
        rut.deleteAll();
    }

    @Test
    void existsCustomerByEmail() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        rut.save(customer);

        boolean existsCustomerById = rut.existsCustomerByEmail(email);

        assertThat(existsCustomerById).isTrue();
    }

    @Test
    void existsCustomerByEmail_emailNotPresent() {
        String email = "nn";

        boolean existsCustomerByEmail = rut.existsCustomerByEmail(email);

        assertThat(existsCustomerByEmail).isFalse();
    }

    @Test
    void existsCustomerById() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        rut.save(customer);

        Long id = rut.findAll()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        boolean existsCustomerById = rut.existsCustomerById(id);

        assertThat(existsCustomerById).isTrue();
    }

    @Test
    void existsCustomerByEmail_idNotPresent() {
        long id = -1;

        boolean existsCustomerById = rut.existsCustomerById(id);

        assertThat(existsCustomerById).isFalse();
    }
}