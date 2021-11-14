package unsw.loopmania.Backend.Building;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class CampfireBuilding extends Building {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public CampfireBuilding(int x, int y) {
        super(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    /*public void visit() {
        super.visit();
        LoopManiaWorld world = LoopManiaWorld.getInstance();
        Character character = world.getCharacter();
        if(Entity.getDistance(this, character) < 2 ){
            character.setAttack(character.getAttack() + 5);
        }


    /*@Override
    public void visit() {
        super.visit();
        LoopManiaWorld world = LoopManiaWorld.getInstance();
        if (Entity.overlap(this, world.getCharacter())){
            world.getCharacter().setHP(world.getCharacter().getHP() + 100);
        }
    }*/
}
