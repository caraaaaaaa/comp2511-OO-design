package unsw.loopmania.Backend.Card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.VampireCastleBuilding;

/**
 * represents a vampire castle card in the backend game world
 */
public class VampireCastleCard extends Card {
    // TODO = add more types of card
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public Building genBuilding(int x, int y) {
        return new VampireCastleBuilding(x, y);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "VampireCastle";
    }
}

