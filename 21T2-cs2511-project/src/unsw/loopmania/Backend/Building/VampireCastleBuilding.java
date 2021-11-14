package unsw.loopmania.Backend.Building;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Backend.Enemy.Vampire;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building {

    //int roundCounter = 0;

    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public VampireCastleBuilding(int x, int y) {
        super(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    @Override
    public Vampire genEnemy(int round, PathPosition position) {
        if (round - this.roundCounter >= 5) {
            this.roundCounter = round;
            return new Vampire(position);
        } else {
            return null;
        }
    }
}

