package com.glo.flight.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightEventDTO {

	private Long id;
	private String flightNumber;
	private String origin;
	private String destination;
	private String departureTime;
	private String arrivalTime;
	private int capacity;
	private int availableSeats;
	private String action;
}
