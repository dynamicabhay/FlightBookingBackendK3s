package com.glo.flight.entity;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "flight")
//@Getter
//@Setter
//@RequiredArgsConstructor
//@AllArgsConstructor
public class Flight {
	@Id
	@GeneratedValue
	private Long id;
	private String flightNumber;
	private String origin;
	private String destination;
	private String departureTime;
	private String arrivalTime;
	private int capacity;
	private int availableSeats;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Flight(Long id, String flightNumber, String origin, String destination, String departureTime,
			String arrivalTime, int capacity, int availableSeats) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.capacity = capacity;
		this.availableSeats = availableSeats;
	}
	public Flight() {
		super();
	}
	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightNumber=" + flightNumber + ", origin=" + origin + ", destination="
				+ destination + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", capacity="
				+ capacity + ", availableSeats=" + availableSeats + "]";
	}	
}