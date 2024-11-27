package com.gl.flightBooking.dto;

import java.util.List;

import lombok.Data;

@Data
public class PassengerBookingsDTO {

	private Long passenger_id;
    private String firstName;
    private String lastName;
    private String email;
    List<BookingWithFlight> bookings; 
    
}
