package unsw.loopmania.Backend.Item;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Character.Character;

public class TreeStump extends Item{
    private double defenceValue;

    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        //TODO Auto-generated constructor stub
        super.setValue(500);
        defenceValue = 8;
    }

    @Override
    public void replaceReword(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        world.updateExpAmount(1000);
        world.updateGoldAmount(this.getValue());
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "TreeStump";
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
