package unsw.loopmania.Backend.Card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.VillageBuilding;

/**
 * represents a village card in the backend game world
 */
public class VillageCard extends Card {
    // TODO = add more types of card
    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public Building genBuilding(int x, int y) {
        return new VillageBuilding(x, y);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Village";
    }
}
