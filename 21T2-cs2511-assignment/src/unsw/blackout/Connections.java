package unsw.blackout;

import java.time.LocalTime;

public class Connections {
    private String deviceId;
    private LocalTime endTime;
    private int minutesActive;
    private String satelliteId;
    private LocalTime startTime;

    public Connections(String deviceId, LocalTime endTime, int minutesActive, String satelliteId, LocalTime startTime) {
        setStartTime(startTime);
        setSatelliteId(satelliteId);
        setMinutesActive(minutesActive);
        setEndTime(endTime);
        setDeviceId(deviceId);
    }

    
    public String getDeviceId() {
        return deviceId;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public String getSatelliteId() {
        return satelliteId;
    }
    public void setSatelliteId(String satelliteId) {
        this.satelliteId = satelliteId;
    }
    public int getMinutesActive() {
        return minutesActive;
    }
    public void setMinutesActive(int minutesActive) {
        this.minutesActive = minutesActive;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    
}
