package programmingexample1;

import java.time.LocalDateTime;

/**
 * Concrete implementation of Room with a large size
 * Just overrides the abstract roomSize function to set set
 */

public class LargeRoom extends Room {

    /**
     * Constructor that sets the time of creation to now
     */
    public LargeRoom() {
        super(LocalDateTime.now());
    }

    /**
     * set room size to large
     */
    @Override
    public String roomSize() {
        return "large";
    }

}
