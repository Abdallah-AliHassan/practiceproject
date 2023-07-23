package com.abdallahsproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
public class TestcontainersTest {

	@Container
	private static final  PostgreSQLContainer<?> postgreSQLContainer =
			new PostgreSQLContainer<>("postgres:latest")
					.withDatabaseName("abdallahsproject-dao-unit-test")
					.withUsername("root")
					.withPassword("password");

	@Test
	void canStartPostgresDB() {
		assertThat(postgreSQLContainer.isRunning()).isTrue();
		assertThat(postgreSQLContainer.isCreated()).isTrue();
	}

}
