package com.gl.flightBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.flightBooking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

	List<Booking> findByPassengerId(Long id);
	List<Booking> findByFlightId(Long id);
}
