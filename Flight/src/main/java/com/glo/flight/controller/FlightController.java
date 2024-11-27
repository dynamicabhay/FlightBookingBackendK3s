package com.glo.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glo.flight.entity.Flight;
import com.glo.flight.service.FlightService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/flight")
@Slf4j
public class FlightController {
	
	@Autowired
	FlightService service;
	
	@PostMapping
	public ResponseEntity<Flight> addNewFlight(@RequestBody Flight f)
	{
		log.info("Getting flight Details"+f);	
		Flight flight=service.saveFlightDetails(f);
		return ResponseEntity.status(HttpStatus.CREATED).body(flight);
	}
	
	@GetMapping  
	public List<Flight> retriveAllFlightDetails()  
	{  
	return service.getListOfFlights();
	}
	
	@GetMapping("/{id}")  
	public Flight retriveFlightDetails(@PathVariable long id)  
	{  
			return service.getFlightByFlightId(id);
	}
	
	@DeleteMapping("/{id}")  
	public String deleteFlight(@PathVariable int id)  
	{  
		service.deleteFlightById(id);
	    return "Flight Deleted Successfully";
	}
	@PutMapping("/{id}")  
	public ResponseEntity<Flight> updateFlightDetails(@PathVariable long id,@RequestBody Flight flight )  
	{  
		Flight updateflight=service.updateFlight(flight, id);
	    return ResponseEntity.ok(updateflight);
	}
	
	@PutMapping("book/{id}")
	public ResponseEntity<Flight> bookFlight(@PathVariable long id){
		return new ResponseEntity<>(service.bookFlight(id),HttpStatus.OK);
	}
	
	@PostConstruct
	public void init() {
		service.createFlights();
	}
	
	
	
	}
