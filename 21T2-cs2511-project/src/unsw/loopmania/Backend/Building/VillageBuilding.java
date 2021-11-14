package unsw.loopmania.Backend.Building;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Character.Character;

/**
 * a basic form of building in the world
 */
public class VillageBuilding extends Building {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public VillageBuilding(int x, int y) {
        super(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    @Override
    public void visit() {
        super.visit();
        Character character = Character.getInstance();
        if (character.getX() == getX() && character.getY() == getY()) {
            if (character.getHealthPoints() < 950) {
                character.damage(-50);
            } else {
                character.fillHealthPoints();
            }
        }
    }
}
