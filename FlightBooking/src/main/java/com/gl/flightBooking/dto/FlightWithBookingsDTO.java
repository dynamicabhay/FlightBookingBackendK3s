package com.gl.flightBooking.dto;

import java.util.List;

import lombok.Data;

@Data
public class FlightWithBookingsDTO {

	private Long id;
	private String flightNumber;
	private String origin;
	private String destination;
	private String departureTime;
	private String arrivalTime;
	private int capacity;
	private int availableSeats;
	List<BookingsWithPassengerDTO> bookings;
}
