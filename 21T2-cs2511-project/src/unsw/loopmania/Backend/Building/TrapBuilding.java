package unsw.loopmania.Backend.Building;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Enemy.BasicEnemy;
import unsw.loopmania.Backend.Entity.Entity;

/**
 * a basic form of building in the world
 */
public class TrapBuilding extends Building {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public TrapBuilding(int x, int y) {
        super(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    @Override
    public void visit() {
        super.visit();
        LoopManiaWorld world = LoopManiaWorld.getInstance();
        for (BasicEnemy enemy : world.getEnemies()) {
            if (Entity.overlap(enemy, this)) {
                enemy.damage(100);
                world.getBuildings().remove(this);
                this.shouldExist = new SimpleBooleanProperty(false);
                break;
            }
        }
    }
}
