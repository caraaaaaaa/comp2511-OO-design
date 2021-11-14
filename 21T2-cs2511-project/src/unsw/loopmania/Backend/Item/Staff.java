package unsw.loopmania.Backend.Item;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Character.Character;

/**
 * represents an equipped or unequipped staff in the backend world
 */
public class Staff extends Item{

    private double attackValue;

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setValue(50);
        attackValue = 3;
    }

    @Override
    public void replaceReword(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        world.updateExpAmount(100);
        world.updateGoldAmount(this.getValue());
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Staff";
    }

    @Override
    public void updateAttributes(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        Character character = world.getCharacter();
        character.updataAttackValue(attackValue);
    }

    @Override
    public void downAttributes(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        Character character = world.getCharacter();
        character.updataAttackValue(-attackValue);
    }    
}
