package com.abdallahsproject.Customer.services;

import java.util.List;
import java.util.Optional;

import com.abdallahsproject.Customer.mappers.CustomerRowMapper;
import com.abdallahsproject.Customer.models.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("jdbc")
public class CustomerJDBCDataAccessService implements CustomerDao {

    private JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate,
                                         CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        var sql = """
                SELECT id, name, email, age
                FROM customer
                """;

        List<Customer> customers = jdbcTemplate.query(sql, customerRowMapper);

        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Long id) {
        var sql = """
                SELECT id, name, email, age
                FROM customer
                WHERE id = ?
                """;

        return jdbcTemplate.query(sql, customerRowMapper, id).stream().findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        var sql = """
                INSERT INTO customer(name, email, age)
                VALUES (?, ?, ?)
                """;
        int result = jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAge()
        );

        System.out.println("jdbcTemplate.update = " + result);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        var sql = """
                SELECT COUNT(id)
                FROM customer
                WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0 ;
    }

    @Override
    public boolean existsCustomerById(Long id) {
        var sql = """
                SELECT COUNT(id)
                FROM customer
                WHERE id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0 ;
    }

    @Override
    public void deleteCustomerById(Long id) {
        var sql = """
                DELETE
                FROM customer
                WHERE id = ?
                """;
        int result = jdbcTemplate.update(sql, id);
        System.out.println("delete Customer with Id = " + result);
    }

    @Override
    public void updateCustomer(Customer update) {
        if (update.getName() != null){
            var sql = "UPDATE customer SET name = ? where id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getName(),
                    update.getId()
            );
            System.out.println("update Customer result = " + result);
        }
        if (update.getAge() != null){
            var sql = "UPDATE customer SET age = ? where id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getAge(),
                    update.getId()
            );
            System.out.println("update Customer result = " + result);
        }
        if (update.getEmail() != null){
            var sql = "UPDATE customer SET email = ? where id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getEmail(),
                    update.getId()
            );
            System.out.println("update Customer result = " + result);
        }
    }
}
