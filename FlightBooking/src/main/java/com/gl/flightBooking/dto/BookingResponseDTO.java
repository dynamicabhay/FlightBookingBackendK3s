package com.gl.flightBooking.dto;

import com.gl.flightBooking.entity.Flight;
import com.gl.flightBooking.entity.Passenger;

import lombok.Data;

@Data
public class BookingResponseDTO {

	private Long bookingId;
	private String bookingDate;
	private Flight flight;
	private Passenger passenger;
	
}
