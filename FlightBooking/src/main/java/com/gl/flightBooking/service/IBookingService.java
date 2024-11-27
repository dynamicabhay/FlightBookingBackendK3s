package com.gl.flightBooking.service;

import java.util.List;

import com.gl.flightBooking.dto.BookingRequestDTO;
import com.gl.flightBooking.dto.BookingResponseDTO;
import com.gl.flightBooking.dto.FlightWithBookingsDTO;
import com.gl.flightBooking.dto.PassengerBookingsDTO;

public interface IBookingService {

	BookingResponseDTO createBooking(BookingRequestDTO request);
	List<BookingResponseDTO> getAllBooking();
	BookingResponseDTO getBookingById(Long bookingId);
	PassengerBookingsDTO getBookingByPassengerId(Long passengerId);
	FlightWithBookingsDTO getBookingsByFlightId(Long flightId);
	void cancelBooking(Long bookingId);
	
}
 