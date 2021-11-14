package programmingexample1;

import java.util.List;
import java.time.LocalDateTime;

public abstract class Room implements Comparable<Room> {

    // The room number of the created room
    private int roomNumber;
    // List of all bookings made in the room
    private List<Booking> bookings;
    // Time room was created, needed for ordering.
    private LocalDateTime timeCreated;


    /**
     * Constructor used so time of creation can be set by subclasses when first instantiated
     * @param timeCreated the time to set for when class is created
     */
    public Room(LocalDateTime timeCreated){
    }


    /**
     * Checks if the given booking clashes with any existing bookings.
     * If there is clash return false, if no clash return true
     * @param booking booking to check
     * @return is booking is available
     */
    public boolean isAvailable(Booking booking) {
        return false;
    }


    /**
     * Add the booking to the room. If the booking is not valid
     * then no changes to the rooms bookings
     * @param booking booking to add
     */
    public void addBooking(Booking booking) {
    }


    /**
     * Remove bookings in list of bookings with matching id
     * @param bookingId id of booking to remove
     */
    public void cancelBooking(int bookingId) {
    }


    /**
     * Gets the size of the room
     * @return size of room
     */
    public abstract String roomSize();


    /**
     * Implements the comparable interface so that rooms can be ordered
     * the ordering is room creation, then booking start date
     */
    @Override
    public int compareTo(Room other){
        return 0;
    }
}
