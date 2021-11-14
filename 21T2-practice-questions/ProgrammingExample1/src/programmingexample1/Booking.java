package programmingexample1;

import java.time.LocalDate;

public class Booking {
    private int id;
    private String customer;
    private LocalDate startingDate;
    private LocalDate checkoutDate;

    public Booking(int id, String customer, LocalDate startingDate, LocalDate checkoutDate) {

    }

    public Booking(String customer, LocalDate startingDate, LocalDate checkoutDate) {

    }

    public int getId() {
        return 0;
    }

    public void setId(int id) {

    }

    public boolean isClashing(Booking booking) {
        return false;
    }
}
