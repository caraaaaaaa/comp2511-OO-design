package unsw.loopmania.Backend.Card;

import java.lang.reflect.InvocationTargetException;

import javafx.beans.property.SimpleIntegerProperty;

public class CardFactory {
    public static Card generateCard(String type, int x, int y) {
        switch (type) {
            case "VampireCastle":
                return new VampireCastleCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "ZombiePit":
                return new ZombiePitCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "Tower":
                return new TowerCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "Village":
                return new VillageCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "Barracks":
                return new BarracksCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "Trap":
                return new TrapCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case "Campfire":
                return new CampfireCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            default:
                return null;
        }
    }

    public static Card generateCard(Class<? extends Card> clazz, int x, int y) {
        try {
            return clazz.getConstructor(SimpleIntegerProperty.class, SimpleIntegerProperty.class)
                    .newInstance(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
