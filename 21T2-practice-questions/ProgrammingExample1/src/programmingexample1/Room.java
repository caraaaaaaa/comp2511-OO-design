package programmingexample1;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Room implements Comparable<Room> {
    private int roomNumber;
    private List<Booking> bookings;
    private LocalDateTime timeCreated;

    public Room(LocalDateTime timeCreated) {

    }

    public boolean isAvailable(Booking booking) {
        return false;
    }

    public void addBooking(Booking booking) {

    }

    public void cancelBooking(Booking booking) {

    }

    public abstract String roomSize();
    
    @Override
    public int compareTo(Room other) {
        return 0;
    }
}
