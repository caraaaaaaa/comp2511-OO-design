package unsw.blackout;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;



public class Satellite {
    protected double position;
    private String id;
    protected double height;
    private ArrayList<String> possibleConnections;
    private double velocity;
    private ArrayList<Connections> connections;
    private String type;
    private double initPosition;

    public Satellite(String id, double height, double position) {
        setId(id);
        setPosition(position);
        setPossibleConnections(possibleConnections);
        setConnections(connections);
        setHeight(height);

        setInitPosition(position);
    }

    
    public double getInitPosition() {
        return initPosition;
    }


    public void setInitPosition(double initPosition) {
        this.initPosition = position;
    }


    /** 
     * @param velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    
    /** 
     * @return String
     */
    public String getType() {
        return type;
    }

    
    /** 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    
    /** 
     * @return double
     */
    public double getVelocity() {
        return this.velocity;
    }


    
    /** 
     * @param deviceId
     * @param DeviceType
     */
    public void addpossibleConnections(String deviceId, String DeviceType) {
        if (!(this.possibleConnections.contains(deviceId))) {
            this.possibleConnections.add(deviceId);
        }
        Collections.sort(this.possibleConnections);
    }



    
    /** 
     * @param deviceId
     */
    public void removepossibleConnections(String deviceId) {
        this.possibleConnections.remove(deviceId);
        
    }


    
    /** 
     * @return ArrayList
     */
    public ArrayList<Connections> getConnections() {
        return connections;
    }



    
    /** 
     * @param connections
     */
    public void setConnections(ArrayList<Connections> connections) {
        this.connections = new ArrayList<Connections>();
    }

    public void addConnections(String deviceId, LocalTime endTime, int minutesActive, String satelliteId, LocalTime startTime) {
        Connections newconnections = new Connections(deviceId, endTime, minutesActive, satelliteId, startTime);
        this.connections.add(newconnections);

    }
    
    /** 
     * @return List
     */
    public ArrayList<String> getPossibleConnections() {
        return possibleConnections;
    }



    
    /** 
     * @param possibleConnections
     */
    public void setPossibleConnections(ArrayList<String> possibleConnections) {
        this.possibleConnections = new ArrayList<>();
    }



    
    /** 
     * @return double
     */
    public double getHeight() {
        return height;
    }

    
    /** 
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    
    /** 
     * @return String
     */
    public String getId() {
        return id;
    }

    
    /** 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    
    /** 
     * @return double
     */
    public double getPosition() {
        return position;
    }

    
    /** 
     * @param position
     */
    public void setPosition(double position) {
        this.position = position;
    }

    
    /** 
     * @param tickDurationInMinutes
     */
    public void updatePosition (int tickDurationInMinutes) {
        this.position = (this.initPosition + (tickDurationInMinutes+1)*this.velocity/height) % 360;
    }

}
