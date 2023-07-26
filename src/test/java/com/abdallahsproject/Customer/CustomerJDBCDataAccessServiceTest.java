package com.abdallahsproject.Customer;

import com.abdallahsproject.AbstractTestcontainers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerJDBCDataAccessServiceTest extends AbstractTestcontainers {

    private CustomerJDBCDataAccessService sut;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();


    @BeforeEach
    void setUp() {
        sut = new CustomerJDBCDataAccessService(getJdbcTemplate(), customerRowMapper);
    }

    @Test
    void selectAllCustomers() {
        Customer customer = new Customer(
                faker.name().fullName(),
                faker.internet().safeEmailAddress() + "-" + UUID.randomUUID(),
                20
        );

        sut.insertCustomer(customer);

        List<Customer> customers = sut.selectAllCustomers();

        assertThat(customers).isNotEmpty();
    }

    @Test
    void selectCustomerById_success() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        sut.insertCustomer(customer);

        Long id = sut.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        Optional<Customer> actual = sut.selectCustomerById(id);

        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
        });
    }

    @Test
    void selectCustomerById_fail() {
        long id = -1;

        Optional<Customer> actual = sut.selectCustomerById(id);

        assertThat(actual).isEmpty();
    }

    @Test
    void existsPersonWithEmail() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        sut.insertCustomer(customer);

        boolean existsPersonWithEmail = sut.existsPersonWithEmail(email);

        assertThat(existsPersonWithEmail).isTrue();
    }

    @Test
    void existsPersonWithEmail_emailNotPresent() {
        String email = "nn";

        boolean existsPersonWithEmail = sut.existsPersonWithEmail(email);

        assertThat(existsPersonWithEmail).isFalse();
    }

    @Test
    void existsCustomerById() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        sut.insertCustomer(customer);

        Long id = sut.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        boolean existsCustomerById = sut.existsCustomerById(id);
        Optional<Customer> actual = sut.selectCustomerById(id);

        assertThat(actual).isPresent();
        assertThat(existsCustomerById).isTrue();
    }

    @Test
    void existsCustomerById_idNotFound() {
        long id = -1;

        boolean existsCustomerById = sut.existsCustomerById(id);

        assertThat(existsCustomerById).isFalse();
    }

    @Test
    void deleteCustomerById_success() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();

        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        sut.insertCustomer(customer);

        Long id = sut.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        sut.deleteCustomerById(id);

        boolean existsPersonWithEmail = sut.existsPersonWithEmail(email);
        Optional<Customer> actual = sut.selectCustomerById(id);

        assertThat(actual).isNotPresent();
        assertThat(existsPersonWithEmail).isFalse();
    }

    @Test
    void updateCustomerName() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        sut.insertCustomer(customer);

        Long id = sut.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        var newName = "testName";

        Customer update = new Customer();
        update.setId(id);
        update.setName(newName);

        sut.updateCustomer(update);

        Optional<Customer> actual = sut.selectCustomerById(id);

        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(newName);
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
        });
    }

    @Test
    void updateCustomerEmail() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        sut.insertCustomer(customer);

        Long id = sut.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        var newEmail = "testEmail";

        Customer update = new Customer();
        update.setId(id);
        update.setEmail(newEmail);

        sut.updateCustomer(update);

        Optional<Customer> actual = sut.selectCustomerById(id);

        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(newEmail);
        });
    }

    @Test
    void updateCustomerAge() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        sut.insertCustomer(customer);

        Long id = sut.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        var newAge = 110;

        Customer update = new Customer();
        update.setId(id);
        update.setAge(newAge);

        sut.updateCustomer(update);

        Optional<Customer> actual = sut.selectCustomerById(id);

        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(newAge);
        });
    }

    @Test
    void updateCustomerAllProp() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        sut.insertCustomer(customer);

        Long id = sut.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        var newName = "testNameNew";
        var newEmail = "testEmailNew";
        var newAge = 111;

        Customer update = new Customer();
        update.setId(id);
        update.setName(newName);
        update.setEmail(newEmail);
        update.setAge(newAge);

        sut.updateCustomer(update);

        Optional<Customer> actual = sut.selectCustomerById(id);

        assertThat(actual).isPresent().hasValue(update);
    }

    @Test
    void updateCustomer_noPropertyChange() {
        String email = faker.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );

        sut.insertCustomer(customer);

        Long id = sut.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        Customer update = new Customer();
        update.setId(id);

        sut.updateCustomer(update);

        Optional<Customer> actual = sut.selectCustomerById(id);

        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
        });
    }
}