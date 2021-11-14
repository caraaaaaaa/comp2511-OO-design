package programmingexample1;

import java.time.LocalDate;


/**
 * Object for storing all booking related data together
 * Also can check if two booking clash with one another
 */

public class Booking {
    // unique id value of the booking
    private int id;
    // Information about customer that made booking
    private String customer;
    // Time of start and end of booking
    private LocalDate startingDate;
    private LocalDate checkoutDate;


    /**
     * Constructor for creating a new booking
     */
    public Booking(int id, String customer, LocalDate startingDate, LocalDate checkoutDate){
    }

    /**
     * Constructor for creating a new booking without knowing the appropriate id
     * This is more suitable for the frontend when creating a booking
     */
    public Booking(String customer, LocalDate startingDate, LocalDate checkoutDate){
    }


    /**
     * Getter method for id of the booking
     * @return id of booking
     */
    public int getId() {
        return 0;
    }

    /**
     * Setter method for id of the booking
     * @param id new id of booking
     */
    public void setId(int id) {
    }


    /**
     * Checks whether two booking's times clash with each other (times overlap)
     * This is inclusive (if ones start time matches the other end time, its not a clash)
     * 
     * @param booking other booking to check clash with
     * @return true if clashing, flase if not clashing
     */
    public boolean isClashing(Booking booking){
        return false;
    }
}
