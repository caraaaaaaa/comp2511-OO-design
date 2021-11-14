package unsw.loopmania.Backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import unsw.loopmania.PathPosition;
import unsw.loopmania.Backend.Building.Building;
import unsw.loopmania.Backend.Building.CampfireBuilding;
import unsw.loopmania.Backend.Card.Card;
import unsw.loopmania.Backend.Card.CardFactory;
import unsw.loopmania.Backend.Character.Character;
import unsw.loopmania.Backend.Enemy.BasicEnemy;
import unsw.loopmania.Backend.Enemy.Doggie;
import unsw.loopmania.Backend.Enemy.Muske;
import unsw.loopmania.Backend.Enemy.Slug;
import unsw.loopmania.Backend.Entity.Entity;
import unsw.loopmania.Backend.Item.Item;
import unsw.loopmania.Backend.Item.ItemFactory;

/**
 * A backend world.
 * <p>
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld {
    // TODO = add additional backend functionality

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    public static final int equippedInventoryWidth = 4;
    public static final int equippedInventoryHeight = 1;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * The amount of Gold that has been obtianed
     */
    private int goldAmount;

    /**
     * The amount of Experience that has been obtianed
     */
    private int expAmount;

    /**
     * The amount of health potion that has been obtianed
     */
    private int healthPotionAmount;

    /**
     * The amount of the one ring that has been obtianed
     */
    private int theOneRingAmount;

    /**
     * The amount of DoggieCoin that has been obtianed
     */
    private int doggieCoinAmount;
    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;
    private List<String> rareItemAllowed = new ArrayList<>();

    private Character character;

    // TODO = add more lists for other entities, for equipped inventory items, etc...
    private List<Item> equippedInventoryItems;

    // TODO = expand the range of enemies
    private List<BasicEnemy> enemies;

    public List<BasicEnemy> getEnemies() {
        return enemies;
    }

    public List<String> getRareItemAllowed() {
        return rareItemAllowed;
    }
    // TODO = expand the range of cards
    private List<Card> cardEntities;

    public List<Card> getCardEntities() {
        return cardEntities;
    }

    // TODO = expand the range of items
    private List<Item> unequippedInventoryItems;

    // TODO = expand the range of buildings
    private List<Building> buildingEntities;

    public List<Building> getBuildings() {
        return buildingEntities;
    }

    // List of Soldier to store soldiers
    private List<Soldier> soldierList;

    // record the number of round from the beginging of the game
    private int round = 1;
    private boolean DoggieAppear = false;


    private String goal_type;
    private int goal_quantity;
    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    public List<Pair<Integer, Integer>> getOrderedPath() {
        return orderedPath;
    }

    /**
     * create the world (constructor)
     *
     * @param width       width of world in number of cells
     * @param height      height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        equippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        soldierList = new ArrayList<>();
        loopManiaWorld = this;
    }

    private static LoopManiaWorld loopManiaWorld;

    public static LoopManiaWorld getInstance() {
        assert loopManiaWorld != null;
        return loopManiaWorld;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<Soldier> getSoldierList() {
        return soldierList;
    }

    public void setSodierList(List<Soldier> soldierList) {
        this.soldierList = soldierList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSoldiers() {
        return Integer.valueOf(soldierList.size()).intValue();
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    /**
     * update the amount of gold when obtaining more
     *
     * @param amount
     */
    public void updateGoldAmount(int amount) {
        this.goldAmount += amount;
    }

    public int getExpAmount() {
        return expAmount;
    }

    /**
     * update the amount of experience when obtaining more
     *
     * @param amount
     */
    public void updateExpAmount(int amount) {
        this.expAmount += amount;
    }

    /**
     * update the amount of health potion when obtaining more
     *
     * @param amount
     */
    public void updateHealthPotionAmount(int amount) {
        this.healthPotionAmount += amount;
    }

    public int getHealthPotionAmount() {
        return healthPotionAmount;
    }

    public void useHealthPotion() {
        if (healthPotionAmount > 0 && character.getHealthPoints() > 0) {
            character.fillHealthPoints();
            healthPotionAmount -= 1;
        }
    }

    /**
     * update the amount of the one ring when obtaining more
     *
     * @param amount
     */
    public void updateTheOneRingAmount(int amount) {
        this.theOneRingAmount += amount;
    }

    public int getTheOneRingAmount() {
        return theOneRingAmount;
    }

    /**
     * update the amount of the DoggieCoin when obtaining more
     *
     * @param amount
     */
    public void updateDoggieCoinAmount(int amount) {
        this.doggieCoinAmount += amount;
    }

    public int getDoggieCoinAmount() {
        return doggieCoinAmount;
    }

    public List<Item> getUnequippedItemsList() {
        return unequippedInventoryItems;
    }

    public List<Item> getEquippedItemsList() {
        return equippedInventoryItems;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     *
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return this.character;
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     *
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        // TODO = if more specialised types being added from main menu, add more methods like this with specific input types...
        nonSpecifiedEntities.add(entity);
    }

    public void setGoal(String goal) {
        this.goal_type = goal;
    }

    public String getGoal() {
        return this.goal_type;
    }

    public void setGoalQuantity(int quantity) {
        this.goal_quantity = quantity;
    }

    public int getGoalQuantity() {
        return this.goal_quantity;
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     *
     * @return list of the enemies to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies() {
        // TODO = expand this very basic version
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        List<BasicEnemy> spawningEnemies = new ArrayList<>();

        if (pos != null) {
            int indexInPath = orderedPath.indexOf(pos);
            Slug enemy = new Slug(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }

        if (round % 20 == 0 && !DoggieAppear) {
            Doggie d = new Doggie(new PathPosition(0, getOrderedPath()));
            enemies.add(d);
            spawningEnemies.add(d);
            DoggieAppear = true;
        }

        if (round % 40 == 0 && expAmount > 10000) {
            Muske d = new Muske(new PathPosition(25, getOrderedPath()));
            enemies.add(d);
            spawningEnemies.add(d);
        }
        //create zombie and vampire
        for (Building building : buildingEntities) {
            PathPosition position = new PathPosition(building.getX(), building.getY(), orderedPath);
            BasicEnemy enemy = building.genEnemy(round, position);
            if (enemy == null) {
                continue;
            }
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }
        return spawningEnemies;
    }

    /**
     * kill an enemy
     *
     * @param enemy enemy to be killed
     */
    private void killEnemy(BasicEnemy enemy) {
        enemy.destroy();
        enemies.remove(enemy);
    }

    /**
     * run the expected battles in the world, based on current world state
     *
     * @return list of enemies which have been killed
     */
    public List<BasicEnemy> runBattles() {
        // TODO = modify this - currently the character automatically wins all battles without any damage!

        for (Building building : buildingEntities) {
            if (building instanceof CampfireBuilding && Entity.getDistance(character, building) < 2) {
                character.updataAttackValue(character.getAttackValue() * 2);
                ;
                break;
            }
        }

        List<BasicEnemy> defeatedEnemies = new ArrayList<BasicEnemy>();
        for (BasicEnemy e : enemies) {
            // Pythagoras: a^2+b^2 < radius^2 to see if within radius
            // TODO = you should implement different RHS on this inequality, based on influence radii and battle radii
            e.battle(this, defeatedEnemies);
            //defeatedEnemies.add(enemy);
        }
        for (BasicEnemy e : defeatedEnemies) {
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from the enemies list
            // if we killEnemy in prior loop, we get java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
        }
        return defeatedEnemies;
    }

    /**
     * spawn a card in the world and return the card entity
     *
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public Card addCard(String type) {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // TODO = give some cash/experience/item rewards for the discarding of the oldest card
            cardEntities.get(0).replaceReword(this);
            removeCard(0);
        }
        Card card = CardFactory.generateCard(type, cardEntities.size(), 0);
        cardEntities.add(card);
        return card;
    }

    public Card addCard(Class<? extends Card> clazz) {
        Card card = CardFactory.generateCard(clazz, cardEntities.size(), 0);
        cardEntities.add(card);
        return card;
    }

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     *
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index) {
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }


    /**
     * spawn an item in the world and return the basic item
     *
     * @return a basic item to be spawned in the controller as a JavaFX node
     */
    public Item addUnequippedItem(String type) {
        // TODO = expand this - we would like to be able to add multiple types of items, apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest item
            unequippedInventoryItems.get(0).replaceReword(this);
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new entity, as we know we have at least made a slot available...
        Item item = ItemFactory.generateItem(type, firstAvailableSlot.getValue0(), firstAvailableSlot.getValue1());
        unequippedInventoryItems.add(item);
        return item;
    }

    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves() {
        character.moveDownPath();

        // update round when character pass hero's castle
        if ((character.getX() == 0) && (character.getY() == 0)) {
            this.round = this.round + 1;
            DoggieAppear = false;
        }

        moveBasicEnemies();

        for (Building building : buildingEntities) {
            building.visit();
        }

    }

    /**
     * remove an item from the unequipped inventory
     *
     * @param item item to be removed
     */
    public void removeUnequippedInventoryItem(Entity item) {
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * remove an item from the equipped inventory
     *
     * @param item item to be removed
     */
    private void removeEquippedInventoryItem(Entity item) {
        item.destroy();
        equippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     *
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Item getUnequippedInventoryItemEntityByCoordinates(int x, int y) {
        for (Item i : unequippedInventoryItems) {
            if ((i.getX() == x) && (i.getY() == y)) {
                return i;
            }
        }
        return null;
    }

    /**
     * return a equipped inventory item by x and y coordinates
     * assumes that no 2 equipped inventory items share x and y coordinates
     *
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return equipped inventory item at the input position
     */
    private Item getEquippedInventoryItemEntityByCoordinates(int x, int y) {
        for (Item i : equippedInventoryItems) {
            if ((i.getX() == x) && (i.getY() == y)) {
                return i;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
     *
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index) {
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     *
     * @return x, y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem() {
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y = 0; y < unequippedInventoryHeight; y++) {
            for (int x = 0; x < unequippedInventoryWidth; x++) {
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null) {
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     *
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x) {
        for (Card c : cardEntities) {
            if (c.getX() >= x) {
                c.x().set(c.getX() - 1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveBasicEnemies() {
        // TODO = expand to more types of enemy
        for (BasicEnemy e : enemies) {
            e.move();
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     *
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition() {
        // TODO = modify this

        // has a chance spawning a basic enemy on a tile the character isn't on or immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(1); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        if ((choice == 0) && (enemies.size() < 2)) {
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            // inclusive start and exclusive end of range of positions not allowed
            int startNotAllowed = (indexPosition - 2 + orderedPath.size()) % orderedPath.size();
            int endNotAllowed = (indexPosition + 3) % orderedPath.size();
            // note terminating condition has to be != rather than < since wrap around...
            for (int i = endNotAllowed; i != startNotAllowed; i = (i + 1) % orderedPath.size()) {
                orderedPathSpawnCandidates.add(orderedPath.get(i));
            }

            // choose random choice
            Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));

            return spawnPosition;
        }
        return null;
    }

    /**
     * remove a card by its x, y coordinates
     *
     * @param cardNodeX     x index from 0 to width-1 of card to be removed
     * @param cardNodeY     y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c : cardEntities) {
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) {
                card = c;
                break;
            }
        }
        assert card != null;

        // now spawn building
        Building newBuilding = card.genBuilding(buildingNodeX, buildingNodeY);
        newBuilding.setRoundCounter(round);
        buildingEntities.add(newBuilding);

        // destroy the card
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);

        return newBuilding;
    }

    /**
     * move an entity from unequippedInventory to equippedInventory
     *
     * @param unequippedNodeX x index from 0 to width-1 of unequippedInventory to be removed
     * @param unequippedNodeY y index from 0 to height-1 of unequippedInventory to be removed
     * @param equippedNodeX   x index from 0 to width-1 of equippedInventory to be added
     * @param equippedNodeY   y index from 0 to height-1 of equippedInventory to be added
     */
    public void addEquippedInventoryByCoordinates(int unequippedNodeX, int unequippedNodeY, int equippedNodeX, int equippedNodeY) {
        Item e = getEquippedInventoryItemEntityByCoordinates(equippedNodeX, equippedNodeY);
        if (e != null) {
            removeEquippedInventoryItem(e);
            e.downAttributes(this);
        }


        // get the item in unequippedInventory
        Item i = getUnequippedInventoryItemEntityByCoordinates(unequippedNodeX, unequippedNodeY);
        // add item into the equipped Inventory 

        Item item = ItemFactory.generateItem(i.getType(), equippedNodeX, equippedNodeY);
        if (item != null) {
            equippedInventoryItems.add(item);
            item.updateAttributes(this);
        }
        // remove the item in unequippedInventory
        removeUnequippedInventoryItem(i);
    }

    public Item sellEntity(String type) {
        Item item = null;
        for (Item i : unequippedInventoryItems) {
            if (i.getType().equals(type)) {
                item = i;
                break;
            }
        }
        if (item != null) {
            removeUnequippedInventoryItem(item);
        }
        return item;
    }
}
