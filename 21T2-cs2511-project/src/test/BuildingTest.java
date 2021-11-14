package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static test.TrivialTest.loadWorld;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Building.BarracksBuilding;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.CampfireBuilding;
import unsw.loopmania.Backend.Building.TowerBuilding;
import unsw.loopmania.Backend.Building.TrapBuilding;
import unsw.loopmania.Backend.Building.VampireCastleBuilding;
import unsw.loopmania.Backend.Building.VillageBuilding;
import unsw.loopmania.Backend.Building.ZombiePitBuilding;
import unsw.loopmania.Backend.Card.Card;
import unsw.loopmania.Backend.Card.VampireCastleCard;
import unsw.loopmania.Backend.Enemy.Slug;

public class BuildingTest {
    static Class<?>[] buildingClasses = {
            ZombiePitBuilding.class, VampireCastleBuilding.class, VillageBuilding.class, CampfireBuilding.class,
            TowerBuilding.class, TrapBuilding.class, BarracksBuilding.class
    };

    @Test
    public void testVillageHealCharacter() {
        var world = loadWorld();
        world.getCharacter().setHealthpoints(900);
        Card card = world.addCard("Village");
        Building building = card.genBuilding(world.getCharacter().getX(), world.getCharacter().getY());
        building.visit();
        assertEquals(950, world.getCharacter().getHealthPoints());

        building.visit();
        assertEquals(1000, world.getCharacter().getHealthPoints());

        building.visit();
        assertEquals(1000, world.getCharacter().getHealthPoints());
    }

    @Test
    public void testBarracksBuilding() {
        var world = loadWorld();
        world.addCard("Barracks");
        for (Card card : world.getCardEntities()) {
            Building building = card.genBuilding(world.getCharacter().getX(), world.getCharacter().getY());
            world.getBuildings().add(building);
        }
        world.setRound(100);
        for (Building building : world.getBuildings()) {
            building.visit();
        }
        assertEquals(1, world.getSoldiers());
    }

    @Test
    public void testZombiePit() {
        var world = loadWorld();
        ZombiePitBuilding building = new ZombiePitBuilding(0, 0);
        assertNull(building.genEnemy(0, new PathPosition(0, world.getOrderedPath())));
        assertNotNull(building.genEnemy(1, new PathPosition(0, world.getOrderedPath())));
    }

    @Test
    public void testVampireCastle() {
        var world = loadWorld();
        VampireCastleBuilding building = new VampireCastleBuilding(0, 0);
        assertNull(building.genEnemy(0, new PathPosition(0, world.getOrderedPath())));
        assertNotNull(building.genEnemy(5, new PathPosition(0, world.getOrderedPath())));
    }

    @Test
    public void testAllBuilding() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var world = loadWorld();
        List<Building> buildings = world.getBuildings();
        for (Class<?> clazz : buildingClasses) {
            Building building = (Building) clazz.getConstructor(SimpleIntegerProperty.class, SimpleIntegerProperty.class)
                    .newInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            assertNotNull(building);
            buildings.add(building);
        }
        world.getEnemies().add(new Slug(new PathPosition(0, world.getOrderedPath())));
        world.getEnemies().add(new Slug(new PathPosition(world.getOrderedPath().size() / 2, world.getOrderedPath())));

        for (Building building : buildings) {
            building.visit();
        }

        world.getCharacter().moveDownPath();
        for (Building building : buildings) {
            building.visit();
        }

        for (int i = 0; i < world.getOrderedPath().size() - 2; i++) {
            world.getCharacter().moveDownPath();
        }
        for (Building building : buildings) {
            building.visit();
        }
    }

    @Test
    public void testConvertCardToBuildingByCoordinates() {
        LoopManiaWorld world = loadWorld();
        world.addCard(VampireCastleCard.class);

        Building building = world.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        assertTrue(building.getX() == 1 && building.getY() == 1);
    }

}
