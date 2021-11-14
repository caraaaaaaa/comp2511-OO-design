package unsw.loopmania.Backend.Item;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Character.Character;

/**
 * represents an equipped or unequipped helmet in the backend world
 */
public class Helmet extends Item{
    
    private double defenceValue;

    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setValue(50);
        defenceValue = 3;
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
        return "Helmet";
    }

    @Override
    public void updateAttributes(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        Character character = world.getCharacter();
        character.updataAttackValue(-defenceValue);   
    }

    @Override
    public void downAttributes(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        Character character = world.getCharacter();
        character.updataAttackValue(defenceValue);   
    }
}
