package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static test.EnemyTest.enemyClasses;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.PathTile;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Soldier;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.CampfireBuilding;
import unsw.loopmania.Backend.Building.VampireCastleBuilding;
import unsw.loopmania.Backend.Card.Card;
import unsw.loopmania.Backend.Card.VampireCastleCard;
import unsw.loopmania.Backend.Character.Character;
import unsw.loopmania.Backend.Enemy.BasicEnemy;
import unsw.loopmania.Backend.Item.Armour;
import unsw.loopmania.Backend.Item.Staff;
import unsw.loopmania.Backend.Item.Stake;
import unsw.loopmania.Backend.Item.Sword;


/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */
public class TrivialTest {


    @Test
    public void blahTest() {
        assertEquals("a", "a");
    }

    @Test
    public void blahTest2() {
        LoopManiaWorld d = new LoopManiaWorld(1, 2, new ArrayList<>());
        assertEquals(d.getWidth(), 1);
    }

    @Test
    public void testAddUnequippeditems() {
        LoopManiaWorld d = new LoopManiaWorld(10, 10, new ArrayList<>());
        d.addUnequippedItem("Sword");
        d.addUnequippedItem("Stake");
        assertTrue(d.getUnequippedItemsList().get(0) instanceof Sword);
        assertTrue(d.getUnequippedItemsList().get(1) instanceof Stake);
    }

    @Test
    public void testReplaceOldestItem() {
        LoopManiaWorld d = new LoopManiaWorld(10, 10, new ArrayList<>());
        d.addUnequippedItem("Sword");
        d.addUnequippedItem("Stake");
        d.addUnequippedItem("Armour");
        d.addUnequippedItem("Staff");
        d.addUnequippedItem("Helmet");
        d.addUnequippedItem("Shield");
        d.addUnequippedItem("Sword");
        d.addUnequippedItem("Stake");
        d.addUnequippedItem("Armour");
        d.addUnequippedItem("Staff");
        d.addUnequippedItem("Helmet");
        d.addUnequippedItem("Shield");
        d.addUnequippedItem("Sword");
        d.addUnequippedItem("Stake");
        d.addUnequippedItem("Armour");
        d.addUnequippedItem("Staff");
        d.addUnequippedItem("Helmet");
        d.addUnequippedItem("Shield");
        d.addUnequippedItem("Sword");
        d.addUnequippedItem("Stake");
        d.addUnequippedItem("Armour");
        d.addUnequippedItem("Staff");
        assertTrue(d.getUnequippedItemsList().get(0) instanceof Sword);
        assertTrue(d.getUnequippedItemsList().get(15) instanceof Staff);
        assertEquals(d.getGoldAmount(), 380);
        assertEquals(d.getExpAmount(), 900);
    }

    @Test
    public void testSellEntity() {
        LoopManiaWorld d = new LoopManiaWorld(10, 10, new ArrayList<>());
        d.addUnequippedItem("Sword");
        d.addUnequippedItem("Stake");
        d.addUnequippedItem("Armour");
        d.addUnequippedItem("Staff");
        d.addUnequippedItem("Helmet");
        d.addUnequippedItem("Shield");
        d.addUnequippedItem("Sword");
        d.addUnequippedItem("Stake");
        assertEquals(d.getUnequippedItemsList().size(), 8);
        assertTrue(d.getUnequippedItemsList().get(0) instanceof Sword);
        d.sellEntity("Sword");
        d.sellEntity("Stake");
        d.sellEntity("Armour");
        d.sellEntity("Staff");
        d.sellEntity("Shield");
        d.sellEntity("Helmet");
        assertEquals(d.getUnequippedItemsList().size(), 2);
    }

    @Test
    public void testRunTick() {
        LoopManiaWorld world = loadWorld();
        Character character = world.getCharacter();
        for (int i = 0; i < world.getOrderedPath().size(); i++) {
            world.runTickMoves();
        }
        world.getBuildings().add(new VampireCastleBuilding(0, 0));
        assertEquals(2, world.getRound());
        world.runTickMoves();
        assertTrue(character.getX() != 0 || character.getY() != 0);
    }

    @Test
    public void testBattle() {
        LoopManiaWorld world = loadWorld();
        Card card = new VampireCastleCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Building building = card.genBuilding(0, 0);
        BasicEnemy enemy = building.genEnemy(100, new PathPosition(0, 0, world.getOrderedPath()));
        world.getEnemies().add(enemy);

        CampfireBuilding campfireBuilding = new CampfireBuilding(0, 0);
        world.getBuildings().add(campfireBuilding);

        world.runBattles();

        world.updateHealthPotionAmount(1);
        assertTrue(world.getCharacter().getHealthPoints() != 1000);
        world.useHealthPotion();
        assertTrue(world.getCharacter().getHealthPoints() == 1000);
    }

    @Test
    public void testArmedBattle() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LoopManiaWorld world = loadWorld();
        world.getEquippedItemsList().add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        world.getSoldierList().add(new Soldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        world.getSoldierList().add(new Soldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));

        for (Class<?> clazz : enemyClasses) {
            BasicEnemy enemy = (BasicEnemy) clazz.getConstructor(PathPosition.class).newInstance(new PathPosition(0, world.getOrderedPath()));
            world.getEnemies().add(enemy);
            BasicEnemy enemy2 = (BasicEnemy) clazz.getConstructor(PathPosition.class).newInstance(new PathPosition(world.getOrderedPath().size() / 2, world.getOrderedPath()));
            world.getEnemies().add(enemy2);
            enemy.move();
        }
        world.runBattles();
    }

    static LoopManiaWorld loadWorld() {
        JSONObject json = null;
        try {
            json = new JSONObject(new JSONTokener(new FileReader("./worlds/world_with_twists_and_turns.json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        int width = json.getInt("width");
        int height = json.getInt("height");

        JSONObject path = json.getJSONObject("path");

        if (!path.getString("type").equals("path_tile")) {
            // ... possible extension
            throw new RuntimeException(
                    "Path object requires path_tile type.  No other path types supported at this moment.");
        }
        PathTile starting = new PathTile(new SimpleIntegerProperty(path.getInt("x")), new SimpleIntegerProperty(path.getInt("y")));
        if (starting.getY() >= height || starting.getY() < 0 || starting.getX() >= width || starting.getX() < 0) {
            throw new IllegalArgumentException("Starting point of path is out of bounds");
        }
        // load connected path tiles
        List<PathTile.Direction> connections = new ArrayList<>();
        for (Object dir : path.getJSONArray("path").toList()) {
            connections.add(Enum.valueOf(PathTile.Direction.class, dir.toString()));
        }

        if (connections.size() == 0) {
            throw new IllegalArgumentException(
                    "This path just consists of a single tile, it needs to consist of multiple to form a loop.");
        }

        // load the first position into the orderedPath
        PathTile.Direction first = connections.get(0);
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(starting.getX(), starting.getY()));

        int x = starting.getX() + first.getXOffset();
        int y = starting.getY() + first.getYOffset();

        // add all coordinates of the path into the orderedPath
        for (int i = 1; i < connections.size(); i++) {
            orderedPath.add(Pair.with(x, y));

            if (y >= height || y < 0 || x >= width || x < 0) {
                throw new IllegalArgumentException("Path goes out of bounds at direction index " + (i - 1) + " (" + connections.get(i - 1) + ")");
            }

            PathTile.Direction dir = connections.get(i);
            x += dir.getXOffset();
            y += dir.getYOffset();
            if (orderedPath.contains(Pair.with(x, y)) && !(x == starting.getX() && y == starting.getY())) {
                throw new IllegalArgumentException("Path crosses itself at direction index " + i + " (" + dir + ")");
            }
        }

        var world = new LoopManiaWorld(width, height, orderedPath);
        world.setCharacter(new Character(new PathPosition(0, orderedPath)));
        return world;
    }
}


