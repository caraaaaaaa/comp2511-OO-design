package unsw.loopmania.Backend.Card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.TrapBuilding;

/**
 * represents a trap card in the backend game world
 */
public class TrapCard extends Card {
    // TODO = add more types of card
    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public Building genBuilding(int x, int y) {
        return new TrapBuilding(x, y);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Trap";
    }
}
