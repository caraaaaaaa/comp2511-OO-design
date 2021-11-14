package programmingexample1;

import java.util.List;

/**
 * Main class for managing the booking system.
 * Holds a list of all the hotels and forwards bookings to the hotels to handle
 * This is the class that the frontend will interact with
 */

public class HotelBookingSystem{

    // List of all hotels in system.
    private List<Hotel> hotels;
    // The id value of the next booking (initially >= 0). This is incremented after
    // each booking to ensure each booking has a unique id and is positive.
    private int nextBookingId;


    /**
     * Default constructor for the hotel booking system
     * Initialises an empty list of hotels and sets nextBookingId to 0
     */
    public HotelBookingSystem() {
    }


    // *** Functions to be used by main ***
    /**
     * Creates a new hotel object and add it to the list of hotels data in the booking system.
     * 
     * @param hotelName name of the new hotel being created
     */
    public void createHotel(String name) {
    }


    /**
     * Forwards creating a new room to the corresponding hotel,
     * if the hotel does not exist, then a new hotel with the name is created and
     * then the room is also created.
     * 
     * @param hotelName name of hotel to add the room to
     * @param number room number of the new room
     * @param size size of the room to be created ("small", "medium", "large")
     */
    public void addRoom(String hotelName, int number, String size) {
    }


    /**
     * Creates booking in the most appropriate room. If no booking can be made nothing changes.
     * 
     * @param booking desired booking to be made
     * @param size desired room size
     * @return the id number of the booking, if booking failed returns number < 0
     */
    public int requestBooking(Booking booking, String size) {
        return 0;
    }


    /**
     * Creates a new booking using requestBooking and cancels the previous booking with the matching id
     * forwards cancelling and creating bookings to the hotels
     * ensures that if cancellation fails, previous booking restored
     * 
     * @param id id value of the previous booking being changed
     * @param booking desired booking to be changed to
     * @param size desired room size
     * @return the id number of the booking, if change failed returns number < 0
     */
    public int changeBooking(int id, Booking booking, String size) {
        return 0;
    }


    /**
     * Removes the booking with the corresponding id from the room
     * This is forwarded to the hotels to cancel the booking
     * 
     * @param id id of booking to cancel
     */
    public void cancelBooking(int id){
    }


    /**
     * Gets list of rooms in the order of room creation, then booking start date 
     * This is forwarded to the hotel with the matching name
     * 
     * @param hotel hotel to get list from
     * @return list of rooms ordered
     */
    public List<Room> hotelOccupanyOrdered(String hotel){
        return null;
    }

    /**
     * Instantiates the this object and connects it to the frontend
     * 
     * @param args
     */
    public static void main(String []args){}
}
