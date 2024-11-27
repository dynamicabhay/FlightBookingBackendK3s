package com.glo.flight.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glo.flight.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight,Long> {


}

