package com.glo.passenger.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.glo.passenger.dto.PassengerEventsDTO;
import com.glo.passenger.exception.PassengerNotFoundException;
import com.glo.passenger.model.Passenger;
import com.glo.passenger.repository.PassengerRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class PassengerServiceImpl implements PassengerService {
	@Autowired
	private PassengerRepo passengerRepo;
	/*
	 * @Autowired private KafkaTemplate<String, String> template;
	 * 
	 * @Value("${spring.kafka.topic.name}") private String topicName;
	 */

	public PassengerEventsDTO mapToEvent(Passenger passenger, String action) {
		return PassengerEventsDTO.builder().action(action).email(passenger.getEmail())
				.firstName(passenger.getFirstName()).lastName(passenger.getLastName())
				.passenger_id(passenger.getPassenger_id()).build();
	}

	@Override
	public Passenger savePassenger(Passenger p) {
		Passenger pass = passengerRepo.save(p);
		Gson gson = new GsonBuilder().create();
		String message = gson.toJson(this.mapToEvent(pass, "CREATED"));
		// System.out.println(message);
		// template.send(topicName,message);
		return pass;
	}

	@Override
	public List<Passenger> getListOfPassengers() {
		return passengerRepo.findAll();
	}

	@Override
	public Passenger getPassengerById(long passenger_id) throws PassengerNotFoundException {
		return passengerRepo.findById(passenger_id)
				.orElseThrow(() -> new PassengerNotFoundException("passenger with id: " + passenger_id + " not found"));

	}

	@Override
	public void deletePassengerById(long passenger_id) throws PassengerNotFoundException {
		Passenger passenger = passengerRepo.findById(passenger_id).orElseThrow(
				() -> new PassengerNotFoundException("passenger with id: " + passenger_id + " not present "));
		passengerRepo.deleteById(passenger_id);
		Gson gson = new GsonBuilder().create();
		String message = gson.toJson(this.mapToEvent(passenger, "DELETED"));
		// template.send(topicName,message);

	}

	@Override
	public Passenger updatePassenger(Passenger p, long passenger_id) throws PassengerNotFoundException {
		Passenger updatePassenger = passengerRepo.findById(passenger_id)
				.orElseThrow(() -> new PassengerNotFoundException("Passenger not exist with id: " + passenger_id));
		updatePassenger.setFirstName(p.getFirstName());
		updatePassenger.setLastName(p.getLastName());
		updatePassenger.setEmail(p.getEmail());
		passengerRepo.save(updatePassenger);
		return updatePassenger;
	}

	@Override
	public void createPassenger() {
		Passenger passenger1 = new Passenger(1L, "John", "Doe", "john.doe@example.com");
		Passenger passenger2 = new Passenger(2L, "Jane", "Smith", "jane.smith@example.com");
		Passenger passenger3 = new Passenger(3L, "Emily", "Johnson", "emily.johnson@example.com");

		passengerRepo.saveAll(Arrays.asList(passenger1, passenger2, passenger3));

	}
}
