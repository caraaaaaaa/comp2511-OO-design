package unsw.blackout;

import java.time.LocalTime;

public class ActivationPeriods {
    
    private LocalTime startTime;
    private LocalTime endTime;

    public ActivationPeriods(LocalTime startTime, LocalTime endTime) {
        this.setStartTime(startTime);
        this.setEndTime(endTime);
    }

    
    /** 
     * @return LocalTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    
    /** 
     * @param endTime
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    
    /** 
     * @return LocalTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    
    /** 
     * @param startTime
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    
}
