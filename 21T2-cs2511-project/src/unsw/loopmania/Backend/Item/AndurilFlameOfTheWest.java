package unsw.loopmania.Backend.Item;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Character.Character;

public class AndurilFlameOfTheWest extends Item{

    private double attackValue;

    public AndurilFlameOfTheWest(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        //TODO Auto-generated constructor stub
        super.setValue(500);
        attackValue = 8;
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
        return "AndurilFlameOfTheWest";
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
