package com.gl.flightBooking.dto;

import lombok.Data;

@Data
public class BookingRequestDTO {

	private Long bookingId;

	private String bookingDate;

	private Long flightId;
	private Long passengerId;
}
