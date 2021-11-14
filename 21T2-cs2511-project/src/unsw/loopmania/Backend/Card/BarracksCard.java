package unsw.loopmania.Backend.Card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Building.BarracksBuilding;
import unsw.loopmania.Backend.Building.Building;

/**
 * represents a barracks card in the backend game world
 */
public class BarracksCard extends Card {
    // TODO = add more types of card
    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public Building genBuilding(int x, int y) {
        return new BarracksBuilding(x, y);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Barracks";
    } 
}
