package com.gl.flightBooking.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gl.flightBooking.configuration.FeignConfig;
import com.gl.flightBooking.entity.Passenger;

@FeignClient(name = "Passenger-Microservice", url="${passenger.service.url}")
@Service
public interface PassengerService {

	@GetMapping("/passenger/{passenger_id}")
	Passenger getPassengerByid(@PathVariable long passenger_id);
}
