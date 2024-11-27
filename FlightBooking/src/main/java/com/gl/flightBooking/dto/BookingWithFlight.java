package com.gl.flightBooking.dto;

import com.gl.flightBooking.entity.Flight;

import lombok.Data;

@Data
public class BookingWithFlight {

	private Long bookingId;
	private String bookingDate;
	private Flight flight;
}
