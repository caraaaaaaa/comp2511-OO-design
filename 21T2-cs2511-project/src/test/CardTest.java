package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static test.TrivialTest.loadWorld;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.VampireCastleBuilding;
import unsw.loopmania.Backend.Building.ZombiePitBuilding;
import unsw.loopmania.Backend.Card.Card;
import unsw.loopmania.Backend.Card.TowerCard;
import unsw.loopmania.Backend.Card.TrapCard;
import unsw.loopmania.Backend.Card.VampireCastleCard;
import unsw.loopmania.Backend.Card.VillageCard;
import unsw.loopmania.Backend.Card.ZombiePitCard;
import unsw.loopmania.Backend.Enemy.BasicEnemy;

public class CardTest {
    static Class<?>[] cardClasses = {
            ZombiePitCard.class, VampireCastleCard.class, TowerCard.class, VillageCard.class, TrapCard.class,
    };

    @Test
    public void testAllCard() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var world = loadWorld();
        for (Class<?> clazz : cardClasses) {
            Card card = (Card) clazz.getConstructor(SimpleIntegerProperty.class, SimpleIntegerProperty.class)
                    .newInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            assertNotNull(card);
            card.getType();
            Building building = card.genBuilding(0, 0);
            if (building instanceof VampireCastleBuilding || building instanceof ZombiePitBuilding) {
                BasicEnemy enemy = building.genEnemy(100, new PathPosition(0, world.getOrderedPath()));
                assertNotNull(enemy);
            }
        }
    }

    @Test
    public void testAddCard() {
        LoopManiaWorld d = new LoopManiaWorld(10, 10, new ArrayList<>());
        d.addCard("VampireCastle");
        d.addCard("Village");
        assertTrue(d.getCardEntities().get(0) instanceof VampireCastleCard);
        assertTrue(d.getCardEntities().get(1) instanceof VillageCard);
    }

    @Test
    public void testReplaceOldestCard() {
        LoopManiaWorld d = new LoopManiaWorld(4, 10, new ArrayList<>());
        assertEquals(d.getUnequippedItemsList().size(), 0);
        d.addCard("VampireCastle");
        d.addCard("Village");
        d.addCard("ZombiePit");
        d.addCard("Tower");
        d.addCard("Barracks");
        d.addCard("Trap");
        d.addCard("Campfire");
        assertEquals(d.getCardEntities().size(), d.getWidth());
        assertTrue(d.getCardEntities().get(0) instanceof TowerCard);
        // assertEquals(d.getUnequippedItemsList().size(), 1);
        assertEquals(d.getGoldAmount(), 300);
        assertEquals(d.getExpAmount(), 600);
    }
}
