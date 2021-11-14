package unsw.loopmania.Backend.Card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.TowerBuilding;

/**
 * represents a tower card in the backend game world
 */
public class TowerCard extends Card {
    // TODO = add more types of card
    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public Building genBuilding(int x, int y) {
        return new TowerBuilding(x, y);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Tower";
    }
}
