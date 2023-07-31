package com.abdallahsproject;

import com.abdallahsproject.Customer.Customer;
import com.abdallahsproject.Customer.CustomerRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository){
		return args -> {
			var faker = new Faker();
			Random random = new Random();
			Name name = faker.name();
			String firstName = name.firstName();
			String lastName = name.lastName();
			Customer customer = new Customer(
					firstName + " " + lastName,
					firstName.toLowerCase() + "." + lastName.toLowerCase()
							+ "@example.com",
					random.nextInt(16, 99)
		);
			customerRepository.save(customer);
		};
	}
}
