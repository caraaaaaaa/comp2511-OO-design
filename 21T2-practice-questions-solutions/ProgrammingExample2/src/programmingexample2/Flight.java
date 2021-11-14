package programmingexample2;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Flight {
    private ArrayList<FlightSeat> seats = new ArrayList<FlightSeat>();
    private String name;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private String destination;

    public Flight(String name, LocalDateTime arrivalTime, LocalDateTime departureTime, String destination) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.destination = destination;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for seats
    public ArrayList<FlightSeat> getSeats() {
        return seats;
    }

    // Getter for arrivalTime
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    // Setter for arrivalTime
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    // Getter for departureTime
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    // Setter for departureTime
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    // adds a seat to the Flight
    public void addSeat(String id, String section) {

    }
}