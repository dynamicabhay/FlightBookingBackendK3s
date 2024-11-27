package com.glo.passenger.model;

import com.glo.passenger.annotations.ValidEmail;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Entity
@Data

    @Table(name = "passenger")
    public class Passenger {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long passenger_id;
        @Column(name = "firstName")
        @NotEmpty(message = "firstName  must not be empty")
   	 	@Size(max = 20, message = "firstName must have maximum 20 character")
        private String firstName;
        @Column(name = "lastName")
        @NotEmpty(message = "lastName  must not be empty")
   	 	@Size(max = 20, message = "lastName must have maximum 20 character")
        private String lastName;
        @Column(name = "email")
        @NotEmpty(message = "email must not be empty")
        @ValidEmail
        private String email;
		public Long getPassenger_id() {
			return passenger_id;
		}
		public void setPassenger_id(Long passenger_id) {
			this.passenger_id = passenger_id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Passenger(Long passenger_id, String firstName, String lastName, String email) {
			super();
			this.passenger_id = passenger_id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
		}
		public Passenger() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Passenger [passenger_id=" + passenger_id + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", email=" + email + "]";
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
        
        
        
        
}
