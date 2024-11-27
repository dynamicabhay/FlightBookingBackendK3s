package com.gl.flightBooking.entity;

import lombok.Data;

@Data
public class Passenger {

	private Long passenger_id;
    private String firstName;
    private String lastName;
    private String email;
}
