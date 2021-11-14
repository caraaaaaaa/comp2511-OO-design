package unsw.loopmania.Backend.Card;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Entity.StaticEntity;
import unsw.loopmania.Backend.Interface.Replace;
import unsw.loopmania.Backend.Item.Item;

/**
 * a Card in the world
 * which doesn't move
 */
public abstract class Card extends StaticEntity implements Replace{

    public static String[] cardsType = {
        "VampireCastle", "ZombiePit", "Tower", "Village", "Barracks", "Trap", "Campfire"
    };

    // TODO = implement other varieties of card than VampireCastleCard
    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public void replaceReword(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        String type = null;
        Random random = new Random();
        int n = random.nextInt(15);
        if (n < Item.BasicItemType.length) {
            type = Item.BasicItemType[n];
        }
        world.updateExpAmount(200);
        world.updateGoldAmount(100);
        if (type != null) {
            if (type == "HealthPotion") {
                world.updateHealthPotionAmount(1);
            } else {
                world.addUnequippedItem(type);
            }
        }
    }
        
    public abstract Building genBuilding(int x, int y);
}

