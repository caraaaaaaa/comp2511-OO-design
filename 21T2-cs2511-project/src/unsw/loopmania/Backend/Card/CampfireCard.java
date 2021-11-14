package unsw.loopmania.Backend.Card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.CampfireBuilding;

/**
 * represents a campfire card in the backend game world
 */
public class CampfireCard extends Card {
    // TODO = add more types of card
    public CampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public Building genBuilding(int x, int y) {
        return new CampfireBuilding(x, y);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Campfire";
    }

}
