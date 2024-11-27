package com.gl.flightBooking.dto;

import com.gl.flightBooking.entity.Passenger;

import lombok.Data;

@Data
public class BookingsWithPassengerDTO {

	private Long bookingId;
	private String bookingDate;
	private Passenger passenger;
}
