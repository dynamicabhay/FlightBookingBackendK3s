package com.glo.flight.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.glo.flight.dto.FlightEventDTO;
import com.glo.flight.entity.Flight;
import com.glo.flight.exceptionhandling.FlightNotFoundException;
import com.glo.flight.repo.FlightRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	FlightRepository repo;

	/*
	 * @Autowired private KafkaTemplate<String,String> template;
	 */
	
	/*
	 * @Value("${spring.kafka.topic.name}") private String topicName;
	 */
	
	
	public FlightEventDTO mapToEvent(Flight flight,String action) {
		return FlightEventDTO.builder()
						.action(action)
						.arrivalTime(flight.getArrivalTime())
						.availableSeats(flight.getAvailableSeats())
						.capacity(flight.getCapacity())
						.departureTime(flight.getDepartureTime())
						.destination(flight.getDestination())
						.flightNumber(flight.getFlightNumber())
						.id(flight.getId())
						.origin(flight.getOrigin())
						.build();
	}
	
	
	@Override
	public Flight saveFlightDetails(Flight f) {
		Flight flight = repo.save(f);
		Gson gson = new GsonBuilder().create();
	    //String message = gson.toJson(this.mapToEvent(flight, "CREATED"));
	   // template.send(topicName,message);
	    return flight;
		
	}

	@Override
	public List<Flight> getListOfFlights() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Flight getFlightByFlightId(long flight_id) {
		// TODO Auto-generated method stub
		return repo.findById(flight_id).orElseThrow(() -> new FlightNotFoundException("flight with id: " + flight_id + " is not present :("));  
		
	}

	@Override
	public void deleteFlightById(long id) {
	
		Flight flight = repo.findById(id).orElseThrow(() -> new FlightNotFoundException("flight with id: " + id + " is not present :("));
		repo.deleteById(id);
		log.info("flight deleted successfully!");
		Gson gson = new GsonBuilder().create();
	    String message = gson.toJson(this.mapToEvent(flight, "DELETED"));
	  //  template.send(topicName,message);
		
	}

	@Override
	public Flight updateFlight(Flight f,long id) {
		// TODO Auto-generated method stub
		 Flight updateFlight = repo.findById(id).orElseThrow(() -> new FlightNotFoundException("Flight not exist with id: " + id));
		updateFlight.setArrivalTime(f.getArrivalTime());
		updateFlight.setAvailableSeats(f.getAvailableSeats());
		updateFlight.setCapacity(f.getCapacity());
		updateFlight.setDepartureTime(f.getDepartureTime());
		updateFlight.setDestination(f.getDestination());
		updateFlight.setFlightNumber(f.getFlightNumber());
		updateFlight.setOrigin(f.getOrigin());
		repo.save(updateFlight);
		log.info(f + " updated successfully !");
		Gson gson = new GsonBuilder().create();
	    String message = gson.toJson(this.mapToEvent(updateFlight, "UPDATED"));
	   // template.send(topicName,message);
	    
		return updateFlight;
	
	}

	@Override
	public Flight bookFlight(long id) {
		Flight flight = this.getFlightByFlightId(id);
		flight.setAvailableSeats(flight.getAvailableSeats()-1);
		repo.save(flight);
		return flight;
	}
	
	public void createFlights() {
		Flight flight1 = new Flight(1L, "AA123", "New York", "Los Angeles", "2024-07-11T08:00:00", "2024-07-11T11:00:00", 150, 150);
        Flight flight2 = new Flight(2L, "BA456", "London", "Paris", "2024-07-11T09:00:00", "2024-07-11T10:30:00", 200, 200);
        Flight flight3 = new Flight(3L, "CA789", "Tokyo", "Sydney", "2024-07-12T22:00:00", "2024-07-13T08:00:00", 180, 180);
        
        repo.saveAll(Arrays.asList(flight1,flight2,flight3));
	}

}
