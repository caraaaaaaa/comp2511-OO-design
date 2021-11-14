package programmingexample2;

import java.util.ArrayList;

public class Passenger {
    private ArrayList<FlightSeat> schedule;

    public Passenger() {
        schedule = new ArrayList<>();
    }

    // Getter for list of flights
    public ArrayList<FlightSeat> getSchedule() {
        return schedule;
    }

    // Add a flight to schedule
    public void bookFlight(FlightSeat seat) {
    }

    // Remove a flight from schedule
    public void cancelFlight(FlightSeat seat) {
    }

    // Update a flight
    public void updateFlight(FlightSeat oldSeat, FlightSeat newSeat) {
        // replace oldSeat with newSeat
    }
}