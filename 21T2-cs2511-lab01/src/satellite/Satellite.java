package satellite;

public class Satellite {
    
    /**
     * Constructor for Satellite
     * @param name
     * @param height
     * @param velocity
     */

    public String name;
    public int height;
    public double position;
    public double velocity;

    public Satellite(String name, int height, double position, double velocity) {
        setName(name);
        setHeight(height);
        setPosition(position);
        setVelocity(velocity);
    }

    /**
     * Getter for name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for position (degrees)
     */
    public double getPositionDegrees() {
        return position;
    }

    /**
     * Getter for position (radians)
     */
    public double getPositionRadians() {
        return Math.toRadians(position);
    }

    /**
     * Returns the linear velocity (metres per second) of the satellite
     */
    public double getLinearVelocity() {
        return velocity;
    }

    /**
     * Returns the angular velocity (degrees per second) of the satellite
     */
    public double getAngularVelocity() {
        return velocity/height/1000;
    }

    /**
     * Setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for height
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Setter for velocity
     * @param velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    /**
     * Setter for position
     * @param position
     */
    public void setPosition(double position) {
        this.position = position;
    }

    /**
     * Calculates the distance travelled by the satellite in the given time
     * @param time (seconds)
     * @return distance in metres
     */
    public double distance(double time) {
        return time * velocity;
    }

    /**
     * main
     */
    public static void main(String[] args) {
        Satellite s01 = new Satellite("Satellite A", 10000, 122.0, 55.0);
        Satellite s02 = new Satellite("Satellite B", 5438, 0.0, 234000.0);
        Satellite s03 = new Satellite("Satellite C", 9029, 284.0, 0);

        System.out.println("I am " + s01.name + " at position " + s01.position + " degrees, " + s01.height + " km above the centre of the earth and moving at a velocity of " + s01.velocity + " metres per second ");
        s01.setHeight(9999);
        s02.setPosition(45.0);
        s03.setVelocity(36.5);
        
        System.out.println(s01.getPositionRadians());
        System.out.println(s02.getAngularVelocity());
        System.out.println(s03.distance(120));
    }

}