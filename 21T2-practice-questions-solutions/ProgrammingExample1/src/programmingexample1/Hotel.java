package programmingexample1;

import java.util.List;
import java.time.LocalDateTime;

/**
 * Hotel object contains its name and all the rooms within the hotel. The hotel
 * will make the booking requests with rooms
 */

public class Hotel implements Comparable<Hotel> {

    // name of the hotel
    private String name;
    // List of Room objects created within this hotel
    private List<Room> rooms;
    // Time hotel was created, needed for ordering
    private LocalDateTime timeCreated;

    /**
     * Constructor for creating a new hotel with set name also set the time created
     * to time now
     * 
     * @param name name of hotel
     */
    public Hotel(String name) {
    }

    /**
     * Create a new room and adds it to this hotel's list of rooms The room of
     * desired size is created by RoomFactory using the factory pattern.
     * 
     * @param number room number of the new room
     * @param size   size of the room to be created ("small", "medium", "large")
     */
    public void createRoom(int number, String size) {
    }

    /**
     * Creates booking in the most appropriate room. If no room available nothing
     * changes and negative number is returned.
     * 
     * @param time time slot of starting date to checkout date of booking
     * @param size desired room size
     * @return the id number of the booking, if no room booked returns number < 0
     */
    public int bookRoom(Booking booking, String size) {
        return 0;
    }

    /**
     * Removes the booking with the corresponding id from the room. This will check
     * over all rooms to see if they have the booking.
     * 
     * @param id booking id of room
     */
    public void cancelRoomBooking(int id) {
    }

    /**
     * Gets list of rooms in the order of room creation, then booking start date
     * 
     * @return list of rooms ordered
     */
    public List<Room> occupanyListOrdered() {
        return null;
    }


    /**
     * Implements the comparable interface so that hotels can be ordered when selecting appropriate rooms
     * hotels made earlier come first
     */
    @Override
    public int compareTo(Hotel other) {
        return 0;
    }
}
