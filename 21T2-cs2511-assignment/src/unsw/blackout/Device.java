package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Device {
    private String type;
    private double position;
    private String id;
    private Boolean isConnected;
    private ArrayList<ActivationPeriods> activationPeriods;


    public Device(String id, String type, double position) {
        setId(id);
        setType(type);
        setPosition(position);
        this.activationPeriods = new ArrayList<ActivationPeriods>();
        setIsConnected(isConnected);
    }


    
    /** 
     * @return ArrayList
     */
    public ArrayList<ActivationPeriods> getActivationPeriods() {
        return activationPeriods;
    }



    
    /** 
     * @param start
     * @param end
     */
    public void addActivationPeriods(LocalTime start, LocalTime end) {
        ActivationPeriods newPeriod = new ActivationPeriods(start, end);
        this.activationPeriods.add(newPeriod);
        sortPeriod(this.activationPeriods);
    }


    
    /** 
     * sort activation periods by start time
     * @param P
     */
    public void sortPeriod(ArrayList<ActivationPeriods> P) {
        Collections.sort(P, new Comparator<ActivationPeriods>() {
            @Override
            public int compare(ActivationPeriods start1 , ActivationPeriods start2) {
                return start1.getStartTime().compareTo(start2.getStartTime());
            }
        }
        );
    }




    
    /** 
     * check if current is between start-time and end-time
     * @param current
     * @param start
     * @param end
     * @return boolean
     */
    public boolean checkTimePeriod(LocalTime current, LocalTime start, LocalTime end) {
        if ((start.compareTo(current) <= 0) && (end.compareTo(current) >= 0)) {
            return true;
        } else {
            return false;
        }
        

    }


    
    /** 
     * @param isConnected
     */
    public void setIsConnected(Boolean isConnected) {
        this.isConnected = false;
    }

    
    /** 
     * @return boolean
     */
    public boolean isIsConnected() {
        return isConnected;
    }

    
    /** 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    
    /** 
     * @return String
     */
    public String getId() {
            return id;
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


}
