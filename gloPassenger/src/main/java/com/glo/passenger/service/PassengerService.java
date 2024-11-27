package com.glo.passenger.service;

import com.glo.passenger.exception.PassengerNotFoundException;
import com.glo.passenger.model.Passenger;

import java.util.List;

public interface PassengerService {
    Passenger savePassenger(Passenger p);
    List<Passenger> getListOfPassengers();
    Passenger getPassengerById(long passenger_id) throws PassengerNotFoundException;
    void deletePassengerById(long id) throws PassengerNotFoundException;
    Passenger updatePassenger(Passenger p, long passenger_id) throws PassengerNotFoundException;
    void createPassenger();
    
}
