package com.glo.passenger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.glo.passenger.exception.PassengerNotFoundException;
import com.glo.passenger.model.Passenger;
import com.glo.passenger.service.PassengerService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/passenger")
    public class PassengerController {
        private final PassengerService passengerService;

        @Autowired
        public PassengerController(PassengerService passengerService) {
            this.passengerService = passengerService;
        }

    @Autowired
    RestTemplate template;
    @PostMapping
    public ResponseEntity<Passenger> addNewPassenger(@Valid @RequestBody Passenger passenger)
    {
        //System.out.println("Getting Passenger Details"+passenger);
        Passenger passenger1=passengerService.savePassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(passenger1);
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> retriveAllPassengers()
    {
       return new ResponseEntity<>(passengerService.getListOfPassengers(),HttpStatus.OK);
    }

    @GetMapping("/{passenger_id}")
    public Passenger retrivePassengerDetails(@PathVariable("passenger_id") long passenger_id) throws PassengerNotFoundException {
        return passengerService.getPassengerById(passenger_id);
    }

    @DeleteMapping("/{passenger_id}")
    public String deleteFlight(@PathVariable("passenger_id") long passenger_id) throws PassengerNotFoundException {
        passengerService.deletePassengerById(passenger_id);
        return "Passenger Deleted Successfully";
    }
    @PutMapping("/{passenger_id}")
    public ResponseEntity<Passenger> updateFlightDetails(@PathVariable("passenger_id") long passenger_id,@RequestBody Passenger passenger ) throws PassengerNotFoundException {
        Passenger updatePassenger=passengerService.updatePassenger(passenger, passenger_id);
        return ResponseEntity.ok(updatePassenger);
    }
    
    @PostConstruct
    public void init() {
    	passengerService.createPassenger();
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


}
