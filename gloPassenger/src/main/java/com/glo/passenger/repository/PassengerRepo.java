package com.glo.passenger.repository;

import com.glo.passenger.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepo extends JpaRepository<Passenger,Long> {
}
