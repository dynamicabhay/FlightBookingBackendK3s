package com.gl.flightBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.gl.flightBooking.exceptions.ResourceNotFoundException;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@SpringBootApplication
@EnableFeignClients
public class FlightBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightBookingApplication.class, args);
	}

	
	@Bean
	CircuitBreaker reportingApiCircuitBreaker(CircuitBreakerRegistry registry) {
		CircuitBreakerConfig config = CircuitBreakerConfig.custom().ignoreExceptions(ResourceNotFoundException.class)
				.build();

		return registry.circuitBreaker("flightPassengerCircuitBreaker", config);
	}

}
