package com.glo.flight.service;

import java.util.List;

import com.glo.flight.entity.Flight;

public interface FlightService {	
	Flight saveFlightDetails(Flight f);
	List<Flight> getListOfFlights();
	Flight getFlightByFlightId(long flight_id);
	void deleteFlightById(long id);
	Flight updateFlight(Flight f, long id);
	Flight bookFlight(long id);
	void createFlights();
}
