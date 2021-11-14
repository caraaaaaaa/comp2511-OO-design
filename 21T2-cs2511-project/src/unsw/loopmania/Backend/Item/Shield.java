package unsw.loopmania.Backend.Item;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Character.Character;

/**
 * represents an equipped or unequipped shield in the backend world
 */
public class Shield extends Item{

    private double defenceValue;

    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setValue(60);
        defenceValue = 4;
    }

    @Override
    public void replaceReword(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        world.updateExpAmount(150);
        world.updateGoldAmount(this.getValue());
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Shield";
    }

    @Override
    public void updateAttributes(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        Character character = world.getCharacter();
        character.updataDefenceValue(defenceValue);
    }

    @Override
    public void downAttributes(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        Character character = world.getCharacter();
        character.updataDefenceValue(-defenceValue);
    } 
}
