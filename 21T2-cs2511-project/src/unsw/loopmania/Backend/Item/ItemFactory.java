package unsw.loopmania.Backend.Item;

import javafx.beans.property.SimpleIntegerProperty;

public class ItemFactory {
    public static Item generateItem(String type, int x, int y) {
        switch (type) {
            case "Sword":
                return new Sword(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "Stake":
                return new Stake(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "Staff":
                return new Staff(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "Armour":
                return new Armour(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y)); 
            case "Shield":
                return new Shield(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "Helmet":
                return new Helmet(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "AndurilFlameOfTheWest":
                return new AndurilFlameOfTheWest(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "TreeStump": 
                return new TreeStump(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            default:
                return null; 
        }
    }
}