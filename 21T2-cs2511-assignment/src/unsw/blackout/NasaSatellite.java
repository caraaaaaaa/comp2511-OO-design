package unsw.blackout;


public class NasaSatellite extends Satellite {


    public NasaSatellite(String id, double height, double position) {
        super(id, height, position);
        super.setVelocity(85.0);
        super.setType("NasaSatellite");
    }


    
}
