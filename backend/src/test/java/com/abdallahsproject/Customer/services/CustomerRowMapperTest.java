package com.abdallahsproject.Customer.services;

import com.abdallahsproject.Customer.models.Customer;
import com.abdallahsproject.Customer.mappers.CustomerRowMapper;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRowMapperTest {

    @Test
    void mapRow() throws SQLException {
        CustomerRowMapper customerRowMapper = new CustomerRowMapper();

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("Ali");
        when(resultSet.getString("email")).thenReturn("Ali@email.com");
        when(resultSet.getInt("age")).thenReturn(19);

        Customer actual = customerRowMapper.mapRow(resultSet, 1);

        Customer expected = new Customer(
                1L, "Ali", "Ali@email.com", 19
        );

        assertThat(actual).isEqualTo(expected);
    }
}