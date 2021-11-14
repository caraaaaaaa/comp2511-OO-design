package unsw.loopmania.Backend.Building;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Soldier;
import unsw.loopmania.Backend.Character.Character;

/**
 * a basic form of building in the world
 */
public class BarracksBuilding extends Building {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public BarracksBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public BarracksBuilding(int x, int y) {
        super(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    @Override
    public void visit() {
        super.visit();
        Character character = Character.getInstance();
        if (character.getX() == getX() && character.getY() == getY()) {
            LoopManiaWorld world = LoopManiaWorld.getInstance();
            if (world.getSoldierList().size() < 4) {
                world.getSoldierList().add(new Soldier(new SimpleIntegerProperty(getX()), new SimpleIntegerProperty(getY())));
            }
        }

    }
}
