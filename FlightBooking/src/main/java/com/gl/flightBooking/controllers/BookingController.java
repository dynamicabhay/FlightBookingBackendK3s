package com.gl.flightBooking.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.flightBooking.dto.BookingRequestDTO;
import com.gl.flightBooking.dto.BookingResponseDTO;
import com.gl.flightBooking.dto.BookingWithFlight;
import com.gl.flightBooking.dto.FlightWithBookingsDTO;
import com.gl.flightBooking.dto.PassengerBookingsDTO;
import com.gl.flightBooking.exceptions.ResourceNotFoundException;
import com.gl.flightBooking.service.IBookingService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/booking")
public class BookingController {

	private final IBookingService bookingService;
	@Autowired(required = false)
	public BookingController(IBookingService bookingService) {
		this.bookingService = bookingService;
	}
	@PostMapping
	public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO request){
		return new ResponseEntity<>(bookingService.createBooking(request),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<BookingResponseDTO>> getAllBookings(){
		return new ResponseEntity<>(bookingService.getAllBooking(),HttpStatus.OK);
				
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable long id){
		return new ResponseEntity<>(bookingService.getBookingById(id),HttpStatus.OK);
	}
	
	@GetMapping("/passenger/{id}")
	@CircuitBreaker(name = "flightPassengerCircuitBreaker",fallbackMethod = "handleFallback")
	public ResponseEntity<PassengerBookingsDTO> getBookingsByPassengerId(@PathVariable long id){
		return new ResponseEntity<>(bookingService.getBookingByPassengerId(id),HttpStatus.OK);
	}
	
	public ResponseEntity<PassengerBookingsDTO> handleFallback(long id, Exception ex){
		if(ex instanceof ResourceNotFoundException) {
			System.out.println("rethrowing ResourceNotFoundException");
			throw new ResourceNotFoundException(ex.getMessage());
		}
		System.out.println("Exception message: " + ex.getMessage());
		System.out.println("exception class: " + ex.getClass().getName());
		System.out.println("Handled by the fallback method ");
		return null;
	}
	
	@GetMapping("/flight/{id}")
	public ResponseEntity<FlightWithBookingsDTO> getBookingsByFlightId(@PathVariable long id){
		
		return new ResponseEntity<>(bookingService.getBookingsByFlightId(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> cancelBooking(@PathVariable Long id){
		bookingService.cancelBooking(id);
		return new ResponseEntity<>( "booking cancelled for id: " + id,HttpStatus.OK);
	}
}
