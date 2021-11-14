package programmingexample1;

import java.time.LocalDateTime;

/**
 * Concrete implementation of Room with a small size
 * Just overrides the abstract roomSize function to set set
 */

public class SmallRoom extends Room {

    /**
     * Constructor that sets the time of creation to now
     */
    public SmallRoom() {
        super(LocalDateTime.now());
    }


    /**
     * set room size to small
     */
    @Override
    public String roomSize() {
        return "small";
    }
    
}
