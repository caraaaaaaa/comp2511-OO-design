package unsw.blackout;


import java.util.Collections;

public class SovietSatellite extends Satellite{
    private double initPosition;


    public SovietSatellite(String id, double height, double position) {
        super(id, height, position);

        if ((super.position < 140) || (super.position > 190)) {
            super.setVelocity(100.0);
        } else {
            super.setVelocity(-100.0);
        }

        super.setType("SovietSatellite");
        setInitPosition(super.position);
        
    }




    
    public double getInitPosition() {
        return initPosition;
    }





    public void setInitPosition(double initPosition) {
        this.initPosition = position;
    }





    /** 
     * @param deviceId
     * @param DeviceType
     */
    @Override
    public void addpossibleConnections(String deviceId, String DeviceType) {
        if (DeviceType.equals("LaptopDevice") | (DeviceType.equals("DesktopDevice"))) {
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

    
    /** 
     * @param tickDurationInMinutes
     */
    @Override
    public void updatePosition (int tickDurationInMinutes) {
        int minu = 0;
        double post = this.getInitPosition();
        if ((post > 190) && (post <= 345)) {
            super.setVelocity(-100.0);
            for (int i = 0; i < tickDurationInMinutes; i++) {
                post = post + super.getVelocity()/super.height;
                minu = i;
                if (post < 140) {
                    super.setVelocity(-super.getVelocity());
                    break;
                }
            }
        } else {
            super.setVelocity(100.0);
            for (int i = 0; i < tickDurationInMinutes; i++) {
                post = post + super.getVelocity()/super.height;
                minu = i;
                if (post > 190) {
                    super.setVelocity(-super.getVelocity());
                    break;
                }
            }
        }
        for (int j = minu+1; j <= tickDurationInMinutes; j++) {
            post = post + super.getVelocity()/super.height;
            if ((post > 190) || (post <= 140)) {
                super.setVelocity(-super.getVelocity());
            }
        }

        super.setPosition(post% 360);
    }

    
}
