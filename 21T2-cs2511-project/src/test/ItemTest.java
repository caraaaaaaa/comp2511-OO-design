package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static test.TrivialTest.loadWorld;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Character.Character;
import unsw.loopmania.Backend.Item.Item;
import unsw.loopmania.Backend.Item.ItemFactory;

public class ItemTest {

    @Test
    public void ItemCreateTest() throws FileNotFoundException {
        // LoopManiaWorld world = loadWorld();
        // Character character = world.getCharacter();
        // assertEquals(character.getAttackValue(), 20);
        Item sword = ItemFactory.generateItem("Sword", 0, 0);
        assertTrue(sword.getType().equals("Sword"));
        assertEquals(sword.getX(), 0);
        Item stake = ItemFactory.generateItem("Stake", 0, 1);
        assertTrue(stake.getType().equals("Stake"));
        assertEquals(stake.getY(), 1);
        Item staff = ItemFactory.generateItem("Staff", 0, 2);
        assertTrue(staff.getType().equals("Staff"));
        Item armour = ItemFactory.generateItem("Armour", 0, 3);
        assertTrue(armour.getType().equals("Armour"));
        Item shield = ItemFactory.generateItem("Shield", 1, 0);
        assertTrue(shield.getType().equals("Shield"));
        assertEquals(shield.getX(), 1);
        Item helmet = ItemFactory.generateItem("Helmet", 1, 1);
        assertTrue(helmet.getType().equals("Helmet"));
        Item andurilFlameOfTheWest = ItemFactory.generateItem("AndurilFlameOfTheWest", 1, 2);
        assertTrue(andurilFlameOfTheWest.getType().equals("AndurilFlameOfTheWest"));
        Item treeStump = ItemFactory.generateItem("TreeStump", 1, 3);
        assertTrue(treeStump.getType().equals("TreeStump"));
        Item healthPotion = ItemFactory.generateItem("HealthPotion", 2, 0);
        assertTrue(healthPotion == null);
    }

    @Test
    public void AddEquippedItemTest() throws FileNotFoundException {
        LoopManiaWorld world = loadWorld();
        Character character = world.getCharacter();
        assertEquals(character.getAttackValue(), 20);
        world.addUnequippedItem("Sword");
        world.addUnequippedItem("Stake");
        world.addUnequippedItem("Armour");
        world.addUnequippedItem("Staff");
        world.addUnequippedItem("Helmet");
        world.addUnequippedItem("Shield");
        world.addUnequippedItem("TreeStump");
        world.addUnequippedItem("AndurilFlameOfTheWest");
        world.addUnequippedItem("Sword");
        world.addUnequippedItem("Sword");
        world.addUnequippedItem("Sword");
        world.addEquippedInventoryByCoordinates(0, 0, 0, 0);
        assertTrue(world.getUnequippedItemsList().size() == 10);
        assertTrue(world.getEquippedItemsList().size() == 1);
        assertEquals(character.getAttackValue(), 25);
        world.addEquippedInventoryByCoordinates(1, 0, 0, 0);
        assertTrue(world.getUnequippedItemsList().size() == 9);
        assertTrue(world.getEquippedItemsList().size() == 1);
        assertEquals(character.getAttackValue(), 24);
        world.addEquippedInventoryByCoordinates(2, 0, 1, 0);
        assertTrue(world.getUnequippedItemsList().size() == 8);
        assertTrue(world.getEquippedItemsList().size() == 2);
        assertEquals(character.getDefenceValue(), 5);
        world.addEquippedInventoryByCoordinates(3, 0, 1, 0);
        assertEquals(character.getDefenceValue(), 0);
        assertEquals(character.getAttackValue(), 27);
        world.addEquippedInventoryByCoordinates(0, 1, 0, 0);
        assertEquals(character.getDefenceValue(), 0);
        assertEquals(character.getAttackValue(), 20);
        world.addEquippedInventoryByCoordinates(1, 1, 0, 0);
        assertEquals(character.getDefenceValue(), 4);
        assertEquals(character.getAttackValue(), 23);
        world.addEquippedInventoryByCoordinates(2, 1, 2, 0);
        assertEquals(character.getDefenceValue(), 12);
        world.addEquippedInventoryByCoordinates(3, 1, 2, 0);
        assertEquals(character.getDefenceValue(), 4);
        assertEquals(character.getAttackValue(), 31);
        world.addEquippedInventoryByCoordinates(0, 2, 0, 0);
        assertEquals(character.getDefenceValue(), 0);
        assertEquals(character.getAttackValue(), 36);
        world.addEquippedInventoryByCoordinates(1, 2, 1, 0);
        assertEquals(character.getAttackValue(), 38);
        world.addEquippedInventoryByCoordinates(2, 2, 2, 0);
        assertEquals(character.getAttackValue(), 35);
    }

    @Test
    public void ItemReplaceTest() throws FileNotFoundException {
        LoopManiaWorld world = loadWorld();
        Item sword = world.addUnequippedItem("Sword");
        Item stake = world.addUnequippedItem("Stake");
        Item armour = world.addUnequippedItem("Armour");
        Item staff = world.addUnequippedItem("Staff");
        Item helmet = world.addUnequippedItem("Helmet");
        Item shield = world.addUnequippedItem("Shield");
        Item treeStump = world.addUnequippedItem("TreeStump");
        Item andurilFlameOfTheWest = world.addUnequippedItem("AndurilFlameOfTheWest");
        sword.replaceReword(world);
        stake.replaceReword(world);
        staff.replaceReword(world);
        armour.replaceReword(world);
        shield.replaceReword(world);
        helmet.replaceReword(world);
        treeStump.replaceReword(world);
        andurilFlameOfTheWest.replaceReword(world);
        assertEquals(world.getGoldAmount(), 1380);
        assertEquals(world.getExpAmount(), 2900);
    }
}
