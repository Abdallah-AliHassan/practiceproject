package com.abdallahsproject.Customer.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.abdallahsproject.Customer.models.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getInt("age")
        );

        return customer;
    }
}
