package com.gl.flightBooking.entity;

import lombok.Data;
import lombok.Setter;

@Data
public class Flight {

	private Long id;
	private String flightNumber;
	private String origin;
	private String destination;
	private String departureTime;
	private String arrivalTime;
	private int capacity;
	private int availableSeats;
}
