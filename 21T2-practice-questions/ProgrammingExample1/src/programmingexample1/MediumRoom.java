package programmingexample1;

import java.time.LocalDateTime;

public class MediumRoom extends Room {

    public MediumRoom(LocalDateTime timeCreated) {
        super(timeCreated);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String roomSize() {
        // TODO Auto-generated method stub
        return "medium";
    }
    
}
