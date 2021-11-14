package unsw.loopmania.Backend.Card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.ZombiePitBuilding;

/**
 * represents a zombie pit card in the backend game world
 */
public class ZombiePitCard  extends Card {
    // TODO = add more types of card
    public ZombiePitCard (SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public Building genBuilding(int x, int y) {
        return new ZombiePitBuilding(x, y);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "ZombiePit";
    }
}

