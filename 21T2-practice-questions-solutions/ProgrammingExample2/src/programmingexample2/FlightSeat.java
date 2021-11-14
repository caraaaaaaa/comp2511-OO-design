package programmingexample2;

public class FlightSeat {
    private String id;
    private String section;
    
    public FlightSeat(String id, String section) {
        this.id = id;
        this.section = section;
    }

    public String getId() {
        return id;
    }

    public String getSection(){
        return section;
    }
}