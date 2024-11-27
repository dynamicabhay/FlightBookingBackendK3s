package com.glo.passenger.service;

import lombok.Data;

@Data
public class PassengerApiResponse {
    private Long passenger_id;
    private String firstName;
    private String lastName;
    private String email;
}
