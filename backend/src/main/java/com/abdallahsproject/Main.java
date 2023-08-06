package com.abdallahsproject;

import com.abdallahsproject.Customer.models.*;
import com.abdallahsproject.Customer.repositories.CarSpecsRepository;
import com.abdallahsproject.Customer.repositories.CustomerRepository;
import com.abdallahsproject.Customer.repositories.ElectricCarRepository;
import com.abdallahsproject.Customer.repositories.FactoryRepository;
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
	CommandLineRunner runner(CustomerRepository customerRepository,
							 ElectricCarRepository electricCarRepository){
		return args -> {
			var faker = new Faker();
			Random random = new Random();
			Name name = faker.name();
			String firstName = name.firstName();
			String lastName = name.lastName();
			String email = firstName.toLowerCase() + "." + lastName.toLowerCase()
					+ "@example.com";
			Customer customer = new Customer(
					firstName + " " + lastName,
					email,
					random.nextInt(16, 99)
			);
			customerRepository.save(customer);

			ElectricCar car = new ElectricCar(email, CarModel.AUDI);
			electricCarRepository.save(car);
		};
	}
}