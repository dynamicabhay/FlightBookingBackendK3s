package com.gl.flightBooking.errors;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDetails {

	private LocalDateTime timestamp;
	private String errorMessage;
	private String description ;
	
}
