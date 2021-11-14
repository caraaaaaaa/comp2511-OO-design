package programmingexample1;

import java.time.LocalDateTime;
import java.util.List;

public class Hotel implements Comparable<Hotel> {
    private String name;
    private List<Room> rooms;
    private LocalDateTime timeCreated;

    public Hotel(String name) {

    }

    public void createRoom(int id, String size) {

    }

    public int bookRoom(Booking booking, String size) {
        return 0;
    }

    public void cancelRoomBooking(int id) {
    }


    public List<Room> occupanyListOrdered() {
        return null;
    }

    @Override
    public int compareTo(Hotel o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
