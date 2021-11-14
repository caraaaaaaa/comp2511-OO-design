package unsw.loopmania.Backend.Building;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Backend.Enemy.BasicEnemy;
import unsw.loopmania.Backend.Entity.StaticEntity;

public abstract class Building extends StaticEntity {

    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public Building(int x, int y) {
        super(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    public BasicEnemy genEnemy(int round, PathPosition position) {
        return null;
    }

    int roundCounter = 0;

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void visit() {
    }
}

