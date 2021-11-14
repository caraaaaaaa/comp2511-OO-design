package programmingexample1;

import java.time.LocalDateTime;

/**
 * Concrete implementation of Room with a medium size
 * Just overrides the abstract roomSize function to set set
 */

public class MediumRoom extends Room {
    
    /**
     * Constructor that sets the time of creation to now
     */
    public MediumRoom() {
        super(LocalDateTime.now());
    }

    /**
     * Set room size to medium
     */
    @Override
    public String roomSize() {
        return "medium";
    }

}
