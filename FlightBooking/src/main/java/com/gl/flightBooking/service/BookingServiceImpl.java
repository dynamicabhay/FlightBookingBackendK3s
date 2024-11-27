package com.gl.flightBooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gl.flightBooking.dto.BookingEventDTO;
import com.gl.flightBooking.dto.BookingRequestDTO;
import com.gl.flightBooking.dto.BookingResponseDTO;
import com.gl.flightBooking.dto.BookingWithFlight;
import com.gl.flightBooking.dto.BookingsWithPassengerDTO;
import com.gl.flightBooking.dto.FlightWithBookingsDTO;
import com.gl.flightBooking.dto.PassengerBookingsDTO;
import com.gl.flightBooking.entity.Booking;
import com.gl.flightBooking.entity.Flight;
import com.gl.flightBooking.entity.Passenger;
import com.gl.flightBooking.exceptions.ResourceNotFoundException;
import com.gl.flightBooking.repository.BookingRepository;
import com.gl.flightBooking.service.external.FlightService;
import com.gl.flightBooking.service.external.PassengerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements IBookingService {

	private final BookingRepository bookRepo;
	private final FlightService flightService;
	private final PassengerService passengerService;
	
	/*
	 * @Value("${spring.kafka.topic.name}") private String topicName;
	 */
	
	/*
	 * @Autowired private KafkaTemplate<String, String> template;
	 */
	public BookingServiceImpl(BookingRepository bookRepo, FlightService flightService,
			PassengerService passengerService) {
		this.bookRepo = bookRepo;
		this.flightService = flightService;
		this.passengerService = passengerService;
	}

	public Booking mapToEnity(BookingRequestDTO dto) {
		return Booking.builder().bookingDate(dto.getBookingDate()).flightId(dto.getFlightId())
				.passengerId(dto.getPassengerId()).build();
	}
	
	public BookingEventDTO mapToEvent(BookingResponseDTO resp,String action) {
		return BookingEventDTO.builder().action(action)
				.bookingDate(resp.getBookingDate())
				.bookingId(resp.getBookingId())
				.passenger(resp.getPassenger())
				.flight(resp.getFlight())
				.build();
	}

	@Override
	public BookingResponseDTO createBooking(BookingRequestDTO request) {
		long flightId = request.getFlightId();
		long passengerId = request.getPassengerId();
		BookingResponseDTO res = new BookingResponseDTO();

		Passenger passenger = passengerService.getPassengerByid(passengerId);
		Flight flight = flightService.bookFlightByid(flightId);
		log.info("flight: " + flight);
		
		log.info("passenger: " + passenger);

		Booking booking = bookRepo.save(this.mapToEnity(request));
		res.setBookingId(booking.getBookingId());
		res.setBookingDate(request.getBookingDate());
		res.setFlight(flight);
		res.setPassenger(passenger);
		Gson gson = new GsonBuilder().create();
        String message = gson.toJson(this.mapToEvent(res, "CREATE"));
        log.info("flight booking created !!, " + res);
       // template.send(topicName,message);
		return res;
	}

	@Override
	public List<BookingResponseDTO> getAllBooking() {
		List<BookingResponseDTO> res = new ArrayList<>();
		List<Booking> bookings = bookRepo.findAll();
		return bookings.stream().map(booking -> {
			BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
			Flight flight = flightService.getFlightByid(booking.getFlightId());
			Passenger passenger = passengerService.getPassengerByid(booking.getPassengerId());
			bookingResponseDTO.setFlight(flight);
			bookingResponseDTO.setPassenger(passenger);
			bookingResponseDTO.setBookingDate(booking.getBookingDate());
			bookingResponseDTO.setBookingId(booking.getBookingId());
			return bookingResponseDTO;
		}).collect(Collectors.toList());

	}

	@Override
	public BookingResponseDTO getBookingById(Long bookingId) {
		BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
		Booking booking = bookRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("booking is not present for the id: " + bookingId));
		Flight flight = flightService.getFlightByid(booking.getFlightId());
		Passenger passenger = passengerService.getPassengerByid(booking.getPassengerId());
		bookingResponseDTO.setFlight(flight);
		bookingResponseDTO.setPassenger(passenger);
		bookingResponseDTO.setBookingDate(booking.getBookingDate());
		bookingResponseDTO.setBookingId(booking.getBookingId());
		return bookingResponseDTO;
	}

	@Override
	public PassengerBookingsDTO getBookingByPassengerId(Long passengerId) {
		PassengerBookingsDTO res = new PassengerBookingsDTO();
		List<BookingWithFlight> bookingWithFlight = new ArrayList<>();
		Passenger passenger = passengerService.getPassengerByid(passengerId);
		List<Booking> bookings = bookRepo.findByPassengerId(passengerId);
		
		bookings.forEach(booking -> {
			BookingWithFlight util = new BookingWithFlight();
			Flight flight = flightService.getFlightByid(booking.getFlightId());
			util.setFlight(flight);
			util.setBookingDate(booking.getBookingDate());
			util.setBookingId(booking.getBookingId());
			bookingWithFlight.add(util);
		});
		res.setEmail(passenger.getEmail());
		res.setFirstName(passenger.getFirstName());
		res.setPassenger_id(passenger.getPassenger_id());
		res.setLastName(passenger.getLastName());
		res.setBookings(bookingWithFlight);
		return res;
	}

	@Override
	public FlightWithBookingsDTO getBookingsByFlightId(Long flightId) {
		FlightWithBookingsDTO res = new FlightWithBookingsDTO();
		List<BookingsWithPassengerDTO> bookingsWithPassengerList = new ArrayList<>();
		Flight flight = flightService.getFlightByid(flightId);
		List<Booking> bookings = bookRepo.findByFlightId(flightId);
		bookings.forEach(booking -> {
			BookingsWithPassengerDTO util = new BookingsWithPassengerDTO();
			Passenger passenger = passengerService.getPassengerByid(booking.getPassengerId());
			util.setPassenger(passenger);
			util.setBookingDate(booking.getBookingDate());
			util.setBookingId(booking.getBookingId());
			bookingsWithPassengerList.add(util);
		});
		res.setBookings(bookingsWithPassengerList);
		res.setArrivalTime(flight.getArrivalTime());
		res.setAvailableSeats(flight.getAvailableSeats());
		res.setCapacity(flight.getCapacity());
		res.setFlightNumber(flight.getFlightNumber());
		res.setDepartureTime(flight.getDepartureTime());
		res.setDestination(flight.getDestination());
		res.setOrigin(flight.getOrigin());
		res.setId(flightId);
		return res;
	}

	@Override
	public void cancelBooking(Long bookingId) {
		BookingResponseDTO booking = this.getBookingById(bookingId);
		bookRepo.deleteById(bookingId);
		Gson gson = new GsonBuilder().create();
        String message = gson.toJson(this.mapToEvent(booking, "CANCELLED"));
       // template.send(topicName,message);
		
	}

}
