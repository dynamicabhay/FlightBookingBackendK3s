package com.gl.flightBooking.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.gl.flightBooking.configuration.FeignConfig;
import com.gl.flightBooking.entity.Flight;

@FeignClient(name = "Flight-MicroService",url="${flight.service.url}")
@Service
public interface FlightService {

	@GetMapping("/flight/{id}")
	Flight getFlightByid(@PathVariable long id);
	
	@PutMapping("/flight/book/{id}")
	Flight bookFlightByid(@PathVariable long id);
}
