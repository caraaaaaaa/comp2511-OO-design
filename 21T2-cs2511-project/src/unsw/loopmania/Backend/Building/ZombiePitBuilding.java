package unsw.loopmania.Backend.Building;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Backend.Enemy.Zombie;

/**
 * a basic form of building in the world
 */
public class ZombiePitBuilding extends Building {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public ZombiePitBuilding(int x, int y) {
        super(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    @Override
    public Zombie genEnemy(int round, PathPosition position) {
        if (round - this.roundCounter >= 1) {
            this.roundCounter = round;
            return new Zombie(position);
        } else {
            return null;
        }
    }
}
