package programmingexample2;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class BookingSystem {
    private List<Flight> flights;
    private List<Passenger> passengers;

    public BookingSystem() {
        flights = new ArrayList<>();
        passengers = new ArrayList<>();
    }

    // Adds a flight to the booking system
    public void addFlight(Flight flight) {
    
    }

    // Removes a flight to the booking system
    public void removeFlight(Flight flight) {
        
    }

    // Adds a passenger to the booking system
    public void addClient(Passenger passenger) {
        
    }

    // Removes a passenger to the booking system
    public void removeClient(Passenger passenger) {
        
    }

    // Add a flight to a passenger's schedule. Returns whether successful
    public boolean bookFlight(Passenger passenger, String destination, LocalDateTime departureTime, String section) {
        // finds flight w/ the correct destination, departure time, and section and assigns seat
        // retrieves seat from flight
        // then passes seat to passenger
        return true;
    }

    // Remove a flight from a passenger's schedule
    public void cancelFlight(Passenger passenger, String destination, LocalDateTime departureTime) {
        // finds seat the passenger has booked a seat with, and the seat booked
        // instructs the passenger object to cancel that seat
    }

    // Update a flight for a passenger. Returns whether successful
    public boolean updateFlight(Passenger passenger, String oldDestination, LocalDateTime oldDepartureTime, String destination, LocalDateTime departureTime, String section) {
        // cancel then book new flight
        // able to "roll-back" if failure - if booking fails will rebook the cancelled flight
        return true;
    }

    
}