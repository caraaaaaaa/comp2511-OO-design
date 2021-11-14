package unsw.loopmania.Backend.Item;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Entity.StaticEntity;
import unsw.loopmania.Backend.Interface.Replace;
import unsw.loopmania.Backend.Interface.UpdateAttributes;

public abstract class Item extends StaticEntity implements Replace, UpdateAttributes{
    private int value;

    public static String[] BasicItemType = {
        "Sword", "Stake", "Staff", "Armour", "Shield", "Helmet","HealthPotion"
    }; 
    
    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y){ 
        super(x, y); 
    }

    public void setValue(int amount) {
        this.value = amount;
    }

    public int getValue() {
        return value;
    }
}
