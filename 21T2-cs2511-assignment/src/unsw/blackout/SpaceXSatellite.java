package unsw.blackout;


import java.util.Collections;

public class SpaceXSatellite extends Satellite{


    public SpaceXSatellite(String id, double height, double position) {
        super(id, height, position);
        super.setVelocity(55.5);
        super.setType("SpaceXSatellite");

    }


    
    /** 
     * @param deviceId
     * @param DeviceType
     */
    @Override
    public void addpossibleConnections(String deviceId, String DeviceType) {
        if (DeviceType.equals("HandheldDevice")) {
            if (!(super.getPossibleConnections().contains(deviceId))) {
                super.getPossibleConnections().add(deviceId);
            }
        }
        
        Collections.sort(super.getPossibleConnections());
    }



    
    /** 
     * @param deviceId
     */
    public void removepossibleConnections(String deviceId) {
        super.getPossibleConnections().remove(deviceId);
        
    }

}
