package com.gl.flightBooking.dto;

import com.gl.flightBooking.entity.Flight;
import com.gl.flightBooking.entity.Passenger;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingEventDTO {

	private Long bookingId;
	private String bookingDate;
	private Flight flight;
	private Passenger passenger;
	private String action;
}
