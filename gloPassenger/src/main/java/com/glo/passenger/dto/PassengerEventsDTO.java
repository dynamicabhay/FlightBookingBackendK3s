package com.glo.passenger.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerEventsDTO {

	private Long passenger_id;
    private String firstName;
    private String lastName;
    private String email;
    private String action;
}
