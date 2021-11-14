package unsw.loopmania;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

import org.codefx.libfx.listener.handle.ListenerHandle;
import org.codefx.libfx.listener.handle.ListenerHandles;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ProgressBar;

import javafx.util.Duration;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Building.*;
import unsw.loopmania.Backend.Card.*;
import unsw.loopmania.Backend.Enemy.*;
import unsw.loopmania.Backend.Entity.*;
import unsw.loopmania.Backend.Item.*;


/**
 * the draggable types.
 * If you add more draggable types, add an enum value here.
 * This is so we can see what type is being dragged.
 */
enum DRAGGABLE_TYPE{
    CARD,
    ITEM
}

/**
 * A JavaFX controller for the world.
 * 
 * All event handlers and the timeline in JavaFX run on the JavaFX application thread:
 *     https://examples.javacodegeeks.com/desktop-java/javafx/javafx-concurrency-example/
 *     Note in https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Application.html under heading "Threading", it specifies animation timelines are run in the application thread.
 * This means that the starter code does not need locks (mutexes) for resources shared between the timeline KeyFrame, and all of the  event handlers (including between different event handlers).
 * This will make the game easier for you to implement. However, if you add time-consuming processes to this, the game may lag or become choppy.
 * 
 * If you need to implement time-consuming processes, we recommend:
 *     using Task https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Task.html by itself or within a Service https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Service.html
 * 
 *     Tasks ensure that any changes to public properties, change notifications for errors or cancellation, event handlers, and states occur on the JavaFX Application thread,
 *         so is a better alternative to using a basic Java Thread: https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
 *     The Service class is used for executing/reusing tasks. You can run tasks without Service, however, if you don't need to reuse it.
 *
 * If you implement time-consuming processes in a Task or thread, you may need to implement locks on resources shared with the application thread (i.e. Timeline KeyFrame and drag Event handlers).
 * You can check whether code is running on the JavaFX application thread by running the helper method printThreadingNotes in this class.
 * 
 * NOTE: http://tutorials.jenkov.com/javafx/concurrency.html and https://www.developer.com/design/multithreading-in-javafx/#:~:text=JavaFX%20has%20a%20unique%20set,in%20the%20JavaFX%20Application%20Thread.
 * 
 * If you need to delay some code but it is not long-running, consider using Platform.runLater https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Platform.html#runLater(java.lang.Runnable)
 *     This is run on the JavaFX application thread when it has enough time.
 */
public class LoopManiaWorldController {

    /**
     * squares gridpane includes path images, enemies, character, empty grass, buildings
     */
    @FXML
    private GridPane squares;

    /**
     * cards gridpane includes cards and the ground underneath the cards
     */
    @FXML
    private GridPane cards;

    @FXML
    private Button music;

    /**
     * anchorPaneRoot is the "background". It is useful since anchorPaneRoot stretches over the entire game world,
     * so we can detect dragging of cards/items over this and accordingly update DragIcon coordinates
     */
    @FXML
    private AnchorPane anchorPaneRoot;

    /**
     * equippedItems gridpane is for equipped items (e.g. swords, shield, axe)
     */
    @FXML
    private GridPane equippedItems;

    @FXML
    private GridPane unequippedInventory;

    @FXML
    private Label goldLabel;

    @FXML
    private Label expLabel;

    @FXML
    private Label soldierLabel;

    @FXML
    private Label potionLabel;

    @FXML
    private Label ringLabel;

    @FXML
    private ProgressBar pb = new ProgressBar(0);

    @FXML
    private Label doggieLabel;




    // all image views including tiles, character, enemies, cards... even though cards in separate gridpane...
    private List<ImageView> entityImages;

    /**
     * when we drag a card/item, the picture for whatever we're dragging is set here and we actually drag this node
     */
    private DragIcon draggedEntity;

    private boolean isPaused;
    public LoopManiaWorld world;

    /**
     * runs the periodic game logic - second-by-second moving of character through maze, as well as enemies, and running of battles
     */
    private Timeline timeline;

    private Image vampireCastleCardImage;
    private Image zombiePitCardImage;
    private Image towerCardImage;
    private Image villageCardImage;
    private Image barracksCardImage;
    private Image trapCardImage;
    private Image campfireCardImage;

    private Image vampireCastleImage;
    private Image zombiePitImage;
    private Image towerImage;
    private Image villageImage;
    private Image barracksImage;
    private Image trapImage;
    private Image campfireImage;

    private Image slugImage; 
    private Image vampireImage;
    private Image zombieImage;
    private Image doggieImage;
    private Image muskeImage;
        
    private Image swordImage;
    private Image stakeImage;
    private Image staffImage;
    private Image armourImage;
    private Image shieldImage;
    private Image helmetImage;
    private Image healthPotionImage;
    private Image andurilFlameOfTheWestImage;
    private Image treeStumpImage;

    private int roundAdd = 1;
    private int roundd = 2;


    /**
     * the image currently being dragged, if there is one, otherwise null.
     * Holding the ImageView being dragged allows us to spawn it again in the drop location if appropriate.
     */
    // TODO = it would be a good idea for you to instead replace this with the building/item which should be dropped
    private ImageView currentlyDraggedImage;
    
    /**
     * null if nothing being dragged, or the type of item being dragged
     */
    private DRAGGABLE_TYPE currentlyDraggedType;

    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped over its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged over the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragOver;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped in the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged into the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragEntered;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged outside of the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragExited;

    /**
     * object handling switching to the main menu
     */
    private MenuSwitcher mainMenuSwitcher;

    private MenuSwitcher shopMenuSwitcher;


    /**
     * @param world world object loaded from file
     * @param initialEntities the initial JavaFX nodes (ImageViews) which should be loaded into the GUI
     */
    public LoopManiaWorldController(LoopManiaWorld world, List<ImageView> initialEntities) {
        this.world = world;
        entityImages = new ArrayList<>(initialEntities);

        slugImage = new Image((new File("src/images/slug.png")).toURI().toString());
        vampireImage = new Image((new File("src/images/vampire.png")).toURI().toString());
        zombieImage = new Image((new File("src/images/zombie.png")).toURI().toString());
        doggieImage = new Image((new File("src/images/doggie.png")).toURI().toString());
        muskeImage = new Image((new File("src/images/ElanMuske.png.png")).toURI().toString());

        vampireCastleImage = new Image((new File("src/images/vampire_castle_building_purple_background.png")).toURI().toString());
        zombiePitImage = new Image((new File("src/images/zombie_pit.png")).toURI().toString());
        towerImage = new Image((new File("src/images/tower.png")).toURI().toString());
        villageImage = new Image((new File("src/images/village.png")).toURI().toString());
        barracksImage = new Image((new File("src/images/barracks.png")).toURI().toString());
        trapImage = new Image((new File("src/images/trap.png")).toURI().toString());
        campfireImage = new Image((new File("src/images/campfire.png")).toURI().toString());

        vampireCastleCardImage = new Image((new File("src/images/vampire_castle_card.png")).toURI().toString());
        zombiePitCardImage = new Image((new File("src/images/zombie_pit_card.png")).toURI().toString());
        towerCardImage = new Image((new File("src/images/tower_card.png")).toURI().toString());
        villageCardImage = new Image((new File("src/images/village_card.png")).toURI().toString());
        barracksCardImage = new Image((new File("src/images/barracks_card.png")).toURI().toString());
        trapCardImage = new Image((new File("src/images/trap_card.png")).toURI().toString());
        campfireCardImage = new Image((new File("src/images/campfire_card.png")).toURI().toString());

        swordImage = new Image((new File("src/images/basic_sword.png")).toURI().toString());
        stakeImage = new Image((new File("src/images/stake.png")).toURI().toString());
        staffImage = new Image((new File("src/images/staff.png")).toURI().toString());
        armourImage = new Image((new File("src/images/armour.png")).toURI().toString());
        shieldImage = new Image((new File("src/images/shield.png")).toURI().toString());
        helmetImage = new Image((new File("src/images/helmet.png")).toURI().toString());
        healthPotionImage = new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());
        andurilFlameOfTheWestImage = new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString());
        treeStumpImage = new Image((new File("src/images/tree_stump.png")).toURI().toString());

        currentlyDraggedImage = null;
        currentlyDraggedType = null;

        // initialize them all...
        gridPaneSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragOver = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragEntered = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragExited = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
    }



    @FXML
    public void initialize() {
        // TODO = load more images/entities during initialization
        
        Image pathTilesImage = new Image((new File("src/images/32x32GrassAndDirtPath.png")).toURI().toString());
        Image inventorySlotImage = new Image((new File("src/images/empty_slot.png")).toURI().toString());

        Image heroCastleImage = (new Image((new File("src/images/heros_castle.png")).toURI().toString()));

        Rectangle2D imagePart = new Rectangle2D(0, 0, 32, 32);

        // Add the ground first so it is below all other entities (inculding all the twists and turns)
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                ImageView groundView = new ImageView(pathTilesImage);
                groundView.setViewport(imagePart);
                squares.add(groundView, x, y);
            }
        }

    

        // load entities loaded from the file in the loader into the squares gridpane
        for (ImageView entity : entityImages){
            squares.getChildren().add(entity);
        }
        
        // add the ground underneath the cards
        for (int x=0; x<world.getWidth(); x++){
            ImageView groundView = new ImageView(pathTilesImage);
            groundView.setViewport(imagePart);
            cards.add(groundView, x, 0);
        }

        ImageView herocastleView = new ImageView(heroCastleImage);
        herocastleView.setViewport(imagePart);
        squares.add(herocastleView, 0, 0);

        // add the empty slot images for the unequipped inventory
        for (int x=0; x<LoopManiaWorld.unequippedInventoryWidth; x++){
            for (int y=0; y<LoopManiaWorld.unequippedInventoryHeight; y++){
                ImageView emptySlotView = new ImageView(inventorySlotImage);
                unequippedInventory.add(emptySlotView, x, y);
            }
        }





        // create the draggable icon
        draggedEntity = new DragIcon();
        draggedEntity.setVisible(false);
        draggedEntity.setOpacity(0.7);
        anchorPaneRoot.getChildren().add(draggedEntity);
    }

    /**
     * create and run the timer
     */
    public void startTimer(){
        // TODO = handle more aspects of the behaviour required by the specification
        System.out.println("starting timer");
        isPaused = false;
        // trigger adding code to process main game logic to queue. JavaFX will target framerate of 0.3 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {
            world.runTickMoves();
            List<BasicEnemy> defeatedEnemies = world.runBattles();
            for (BasicEnemy e: defeatedEnemies){
                reactToEnemyDefeat(e);
            }
            List<BasicEnemy> newEnemies = world.possiblySpawnEnemies();
            for (BasicEnemy newEnemy: newEnemies){
                onLoad(newEnemy);
            }

            double p = (float) world.getCharacter().getHealthPoints() / (float) 1000;
            
            pb.setProgress(p);
            goldLabel.setText("              " + String.valueOf(world.getGoldAmount()));
            expLabel.setText("              " + String.valueOf(world.getExpAmount()));

            
            potionLabel.setText("              " + String.valueOf(world.getHealthPotionAmount()));
            ringLabel.setText("              " + String.valueOf(world.getTheOneRingAmount()));
            soldierLabel.setText("              " + String.valueOf(world.getSoldierList().size()));

            doggieLabel.setText("              " + String.valueOf(world.getDoggieCoinAmount()));
            

            printThreadingNotes("HANDLED TIMER");
            
            if ((world.getCharacter().getX()==0) && (world.getCharacter().getY()==0)) {
                
                if (world.getRound() == roundd) {
                    pause();
                    try {
                        newShopStage();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    roundAdd++;
                    roundd = roundd + roundAdd;
                }
            }

            if (world.getCharacter().getHealthPoints() <= 0) {
                pause();
                if (world.getTheOneRingAmount() > 0) {
                    try {
                        newDeathStage();

                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        newDeathNoStage();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                
            }


            String goal = world.getGoal();
            switch(goal){
                case "experience":
                    if(world.getGoalQuantity() < world.getExpAmount()){
                        pause();
                        try {
                            newGoalStage();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                case "gold": 
                    if(world.getGoalQuantity() < world.getGoldAmount()){
                        pause();
                        try {
                            newGoalStage();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                case "round":
                    if(world.getRound() >= world.getGoalQuantity()){
                        pause();
                        try {
                            newGoalStage();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        
    }


    public void newGoalStage() throws IOException {
        Stage newStage = new Stage();
                
        Group g = new Group();
        Parent root = FXMLLoader.load(getClass().getResource("Win.fxml"));
        g.getChildren().add(root);

        Button no = new Button();
        no.setLayoutX(200);
        no.setLayoutY(200);
        no.setPrefHeight(40.0);
        no.setPrefWidth(120.0);
        no.setText("restart game");
        no.addEventHandler(MouseEvent.MOUSE_CLICKED, 
            new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                try {
                    switchToMainMenu();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } );
        g.getChildren().add(no);

        Scene scene = new Scene(g);
        newStage.setTitle("LoopManiaWorld");
        newStage.setScene(scene);
        
        newStage.show();
    }

    public void newDeathNoStage() throws IOException {
        Stage newStage = new Stage();
        Group g = new Group();
        newStage.setHeight(300);
        newStage.setWidth(400);

        Parent root = FXMLLoader.load(getClass().getResource("DeathNoMenu.fxml"));
        g.getChildren().add(root);
        Button no = new Button();


        no.setLayoutX(100);
        no.setLayoutY(200);
        no.setPrefHeight(40.0);
        no.setPrefWidth(120.0);
        no.setText("restart game");
        no.addEventHandler(MouseEvent.MOUSE_CLICKED, 
            new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                try {
                    switchToMainMenu();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } );
        
        g.getChildren().add(no);

        Scene scene = new Scene(g);
        newStage.setTitle("LoopManiaWorld");
        newStage.setScene(scene);
        
        newStage.show();
    }

    public void newDeathStage() throws IOException {
        Stage newStage = new Stage();
        Group g = new Group();
        newStage.setHeight(300);
        newStage.setWidth(400);

        Parent root = FXMLLoader.load(getClass().getResource("DeathMenu.fxml"));
        g.getChildren().add(root);
        Button yes = new Button();
        Button no = new Button();

        yes.setLayoutX(200);
        yes.setLayoutY(200);
        yes.setPrefHeight(20.0);
        yes.setPrefWidth(80.0);
        yes.setText("Sure!");
        yes.addEventHandler(MouseEvent.MOUSE_CLICKED, 
            new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                world.getCharacter().fillHealthPoints();
                world.updateTheOneRingAmount(-1);
            }
        } );

        no.setLayoutX(100);
        no.setLayoutY(200);
        no.setPrefHeight(20.0);
        no.setPrefWidth(80.0);
        no.setText("NO");
        no.addEventHandler(MouseEvent.MOUSE_CLICKED, 
            new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                try {
                    switchToMainMenu();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } );

        g.getChildren().add(yes);
        g.getChildren().add(no);

        Scene scene = new Scene(g);
        newStage.setTitle("LoopManiaWorld");
        newStage.setScene(scene);
        
        newStage.show();
    }

    public void newShopStage() throws IOException {
        Stage newStage = new Stage();
                
        Group g = new Group();
        newStage.setHeight(500);
        newStage.setWidth(400);


        
        Parent root = FXMLLoader.load(getClass().getResource("ShopMenu.fxml"));
        g.getChildren().add(root);

        Button buySword = newBuyButton("buy", 90, 80, "Sword");
        Button sellSword = newSellButton("sell", 90, 115, "Sword");
        g.getChildren().add(buySword);
        g.getChildren().add(sellSword);

        Button buyStake = newBuyButton("buy", 180, 80, "Stake");
        Button sellStake = newSellButton("sell", 180, 115, "Stake");
        g.getChildren().add(buyStake);
        g.getChildren().add(sellStake);

        Button buyStaff = newBuyButton("buy", 270, 80, "Staff");
        Button sellStaff = newSellButton("sell", 270, 115, "Staff");
        g.getChildren().add(buyStaff);
        g.getChildren().add(sellStaff);

        Button buyAmour = newBuyButton("buy", 90, 230, "Armour");
        Button sellAmour = newSellButton("sell", 90, 265, "Armour");
        g.getChildren().add(buyAmour);
        g.getChildren().add(sellAmour);

        Button buyShield = newBuyButton("buy", 180, 230, "Shield");
        Button sellShield = newSellButton("sell", 180, 265, "Shield");
        g.getChildren().add(buyShield);
        g.getChildren().add(sellShield);

        Button buyHelmet = newBuyButton("buy", 270, 230, "Helmet");
        Button sellHelmet = newSellButton("sell", 270, 265, "Helmet");
        g.getChildren().add(buyHelmet);
        g.getChildren().add(sellHelmet);

        Button buyPotion = newBuyButton("buy", 90, 380, "HealthPotion");
        Button sellPotion = newSellButton("sell", 90, 415, "HealthPotion");
        g.getChildren().add(buyPotion);
        g.getChildren().add(sellPotion);


        Button sellDoggie = newSellButton("sell", 180, 415, "DoggieCoin");
        g.getChildren().add(sellDoggie);

        Scene scene = new Scene(g, 300, 200);
        newStage.setTitle("Shop");
        newStage.setScene(scene);
        
        newStage.show();
    }

    public Button newBuyButton(String bName, int X, int Y, String itemType) {
        Button b = new Button();
                
        b.setLayoutX(X);
        b.setLayoutY(Y);

        b.setPrefHeight(20.0);
        b.setPrefWidth(80.0);
        
        b.setMnemonicParsing(false);
        b.setText(bName);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, 
            new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                buyItem(itemType);
                goldLabel.setText("              " + String.valueOf(world.getGoldAmount()));
                if (itemType.equals("HealthPotion")) {
                    potionLabel.setText("              " + String.valueOf(world.getHealthPotionAmount()));
                }
            }
        } );

        return b;
    }

    public Button newSellButton(String bName, int X, int Y, String itemType) {
        Button b = new Button();
                
        b.setLayoutX(X);
        b.setLayoutY(Y);

        b.setPrefHeight(20.0);
        b.setPrefWidth(80.0);
        
        b.setMnemonicParsing(false);
        b.setText(bName);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, 
            new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                sellItem(itemType);
                goldLabel.setText("              " + String.valueOf(world.getGoldAmount()));
                if (itemType.equals("HealthPotion")) {
                    potionLabel.setText("              " + String.valueOf(world.getHealthPotionAmount()));
                }
            }
        } );

        return b;
    }



    /**
     * pause the execution of the game loop
     * the human player can still drag and drop items during the game pause
     */
    public void pause(){
        isPaused = true;
        System.out.println("pausing");
        timeline.stop();
    }

    public void terminate(){
        pause();
    }

    /**
     * pair the entity an view so that the view copies the movements of the entity.
     * add view to list of entity images
     * @param entity backend entity to be paired with view
     * @param view frontend imageview to be paired with backend entity
     */
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entityImages.add(view);
    }

    /**
     * load a vampire card from the world, and pair it with an image in the GUI
     */
    private void loadCard(String type) {
        // TODO = load more types of card
        Card card = world.addCard(type);
        onLoad(card);
        List<Item> unequippedList = world.getUnequippedItemsList();
        for(Entity e: unequippedList) {
            onLoad(e);
        }
    }

    /**
     * load an item from the world, and pair it with an image in the GUI
     */
    private void loadItem(String type){
        // TODO = load more types of item
        // start by getting first available coordinates
        Item item = world.addUnequippedItem(type);
        onLoad(item);
    }

    /**
     * sell an item from the world, and pair it with an image in the GUI
     */
    private void sellItem(String type){
        // TODO = load more types of weapon
        // start by getting first available coordinates
        if (type.equals("HealthPotion")) {
            if (world.getHealthPotionAmount() > 0) {
                world.updateHealthPotionAmount(-1);
                world.updateGoldAmount(100);
            }
        } else if (type.equals("DoggieCoin")) {
            if (world.getDoggieCoinAmount() > 0) {
                world.updateDoggieCoinAmount(-1);
                world.updateGoldAmount(new Random().nextInt(1000));
            }
        } else {
            Item item = world.sellEntity(type);
            if (item != null) {
                downLoad(item);
                world.updateGoldAmount(item.getValue());
            }
        }
    }

    /**
     * buy an item from the world, and pair it with an image in the GUI
     */
    private void buyItem(String type){
        // TODO = load more types of weapon
        // start by getting first available coordinates
        if (type.equals("HealthPotion")) {
            if (world.getGoldAmount() >= 100) {
                world.updateHealthPotionAmount(1);
                world.updateGoldAmount(-100);
            }
        } else {
            Item item = world.addUnequippedItem(type);
            if (world.getGoldAmount() >= item.getValue()) {
                onLoad(item);
                world.updateGoldAmount(-item.getValue());
            } else {
                world.removeUnequippedInventoryItem(item);
            } 
        }
    }

    /**
     * run GUI events after an enemy is defeated, such as spawning items/experience/gold
     * @param enemy defeated enemy for which we should react to the death of
     */
    private void reactToEnemyDefeat(BasicEnemy enemy) {
        // react to character defeating an enemy
        // in starter code, spawning extra card/weapon...
        // TODO = provide different benefits to defeating the enemy based on the type of enemy
        if (enemy instanceof Slug) {
            world.updateGoldAmount(300);
            world.updateExpAmount(500);
        } else if (enemy instanceof Zombie) {
            world.updateGoldAmount(500);
            world.updateExpAmount(1000);
        } else if (enemy instanceof Vampire){
            world.updateGoldAmount(1000);
            world.updateExpAmount(2000);
        } else if (enemy instanceof Doggie) {
            world.updateGoldAmount(2000);
            world.updateExpAmount(5000);
            if (Math.random() * 5 < 1) {
                world.updateDoggieCoinAmount(1);
            }
        } else {
            world.updateGoldAmount(5000);
            world.updateExpAmount(10000);
        }

        int n = new Random().nextInt(20);
        if (n < Item.BasicItemType.length) {
            if (Item.BasicItemType[n] == "HealthPotion") {
                world.updateHealthPotionAmount(1);
            } else {

                int rare_num = 0;
                if(!world.getRareItemAllowed().isEmpty()){
                    for(String s : world.getRareItemAllowed()){
                        if (s.equals("anduril_flame_of_the_west")){
                            rare_num = rare_num + 3;
                        } else if (s.equals("tree_trump")){
                            rare_num = rare_num + 4;
                        } else if (s.equals("the_one_ring")){
                            rare_num = rare_num + 2;
                        }
                    }
                }
                if (Math.random() * 20 < 1 && (rare_num == 3 || rare_num == 5 ||rare_num == 7 || rare_num == 9)) {
                    loadItem("AndurilFlameOfTheWest");
                } else if (Math.random() * 15 < 1 && (rare_num == 4 || rare_num == 6 ||rare_num == 7 || rare_num == 9)) {
                    loadItem("TreeStump");
                } else if (Math.random() * 10 < 1 && (rare_num == 2 || rare_num == 5 ||rare_num == 6 || rare_num == 9)) {
                    world.updateTheOneRingAmount(1);
                } else {
                    loadItem(Item.BasicItemType[n]);
                }
            }
        }
        
        int r = new Random().nextInt(10);
        if (r < Card.cardsType.length) {
            loadCard(Card.cardsType[r]);
        }
    }

    /**
     * load a vampire castle card into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the cards GridPane.
     * @param vampireCastleCard
     */
    private void onLoad(Card card) {
        Image image = null;
        if (card instanceof VampireCastleCard) {
            image = vampireCastleCardImage;
        }
        if (card instanceof ZombiePitCard) {
            image = zombiePitCardImage;
        }
        if (card instanceof TowerCard) {
            image = towerCardImage;
        }
        if (card instanceof VillageCard) {
            image = villageCardImage;
        }
        if (card instanceof BarracksCard) {
            image = barracksCardImage;
        }
        if (card instanceof TrapCard) {
            image = trapCardImage;
        }
        if (card instanceof CampfireCard) {
            image = campfireCardImage;
        }
        ImageView view = new ImageView(image);
        // FROM https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
        // note target setOnDragOver and setOnDragEntered defined in initialize method
        addDragEventHandlers(view, DRAGGABLE_TYPE.CARD, cards, squares);
        addEntity(card, view);
        cards.getChildren().add(view);
    }

    /**
     * load an item into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param entity
     */
    private void onLoad(Entity entity) {
        Image image = null;
        if (entity instanceof Sword) {
            image = swordImage;
        }
        if (entity instanceof Stake) {
            image = stakeImage;
        }
        if (entity instanceof Staff) {
            image = staffImage;
        }
        if (entity instanceof Armour) {
            image = armourImage;
        }
        if (entity instanceof Shield) {
            image = shieldImage;
        }
        if (entity instanceof Helmet) {
            image = helmetImage;
        }
        if (entity instanceof AndurilFlameOfTheWest) {
            image = andurilFlameOfTheWestImage;
        }
        if (entity instanceof TreeStump) {
            image = treeStumpImage;
        }
        ImageView view = new ImageView(image);
        addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedItems);
        addEntity(entity, view);
        unequippedInventory.getChildren().add(view);  
    }

    /**
     * remove an item from the GUI.
     * remove the image from the unequippedInventory GridPane.
     * @param entity
     */
    private void downLoad(Entity entity) {
        Image image = null;
        if (entity instanceof Sword) {
            image = swordImage;
        }
        if (entity instanceof Stake) {
            image = stakeImage;
        }
        if (entity instanceof Staff) {
            image = staffImage;
        }
        if (entity instanceof Armour) {
            image = armourImage;
        }
        if (entity instanceof Shield) {
            image = shieldImage;
        }
        if (entity instanceof Helmet) {
            image = helmetImage;
        }
        ImageView view = new ImageView(image);
        addEntity(entity, view);
        unequippedInventory.getChildren().remove(view);  
    }

    /**
     * load an enemy into the GUI
     * @param enemy
     */
    private void onLoad(BasicEnemy enemy) {
        if (enemy instanceof Slug) {
            ImageView view = new ImageView(slugImage);
            addEntity(enemy, view);
            squares.getChildren().add(view);
        } else if (enemy instanceof Vampire) {
            ImageView view = new ImageView(vampireImage);
            addEntity(enemy, view);
            squares.getChildren().add(view);
        } else if (enemy instanceof Zombie) {
            ImageView view = new ImageView(zombieImage);
            addEntity(enemy, view);
            squares.getChildren().add(view);
        } else if (enemy instanceof Doggie) {
            ImageView view = new ImageView(doggieImage);
            addEntity(enemy, view);
            squares.getChildren().add(view);
        } else if (enemy instanceof Muske) {
            ImageView view = new ImageView(muskeImage);
            addEntity(enemy, view);
            squares.getChildren().add(view);
        } 
        
    }

    /**
     * load a building into the GUI
     * @param building
     */
    private void onLoad(Building building){
        Image image = null;
        if (building instanceof VampireCastleBuilding) {
            image = vampireCastleImage;
        }
        if (building instanceof ZombiePitBuilding) {
            image = zombiePitImage;
        }
        if (building instanceof TowerBuilding) {
            image = towerImage;
        }
        if (building instanceof VillageBuilding) {
            image = villageImage;
        }
        if (building instanceof BarracksBuilding) {
            image = barracksImage;
        }
        if (building instanceof TrapBuilding) {
            image = trapImage;
        }
        if (building instanceof CampfireBuilding) {
            image = campfireImage;
        }
        ImageView view = new ImageView(image);
        addEntity(building, view);
        squares.getChildren().add(view);
    }

    /**
     * add drag event handlers for dropping into gridpanes, dragging over the background, dropping over the background.
     * These are not attached to invidual items such as swords/cards.
     * @param draggableType the type being dragged - card or item
     * @param sourceGridPane the gridpane being dragged from
     * @param targetGridPane the gridpane the human player should be dragging to (but we of course cannot guarantee they will do so)
     */
    private void buildNonEntityDragHandlers(DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane){
        // TODO = be more selective about where something can be dropped
        // for example, in the specification, villages can only be dropped on path, whilst vampire castles cannot go on the path

        gridPaneSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // TODO = for being more selective about where something can be dropped, consider applying additional if-statement logic
                /*
                 *you might want to design the application so dropping at an invalid location drops at the most recent valid location hovered over,
                 * or simply allow the card/item to return to its slot (the latter is easier, as you won't have to store the last valid drop location!)
                 */
                if (currentlyDraggedType == draggableType){
                    // problem = event is drop completed is false when should be true...
                    // https://bugs.openjdk.java.net/browse/JDK-8117019
                    // putting drop completed at start not making complete on VLAB...

                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != targetGridPane && db.hasImage()){

                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int x = cIndex == null ? 0 : cIndex;
                        int y = rIndex == null ? 0 : rIndex;
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        ImageView image = new ImageView(db.getImage());

                        int nodeX = GridPane.getColumnIndex(currentlyDraggedImage);
                        int nodeY = GridPane.getRowIndex(currentlyDraggedImage);
                        switch (draggableType){
                            case CARD:
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                // TODO = spawn a building here of different types
                                Building newBuilding = convertCardToBuildingByCoordinates(nodeX, nodeY, x, y);
                                onLoad(newBuilding);
                                break;
                            case ITEM:
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                // TODO = spawn an item in the new location. The above code for spawning a building will help, it is very similar
                                if (targetGridPane.equals(equippedItems)) {
                                    world.addEquippedInventoryByCoordinates(nodeX, nodeY, x, y);
                                }
                                targetGridPane.add(image, x, y, 1, 1);
                                break;
                            default:
                                break;
                        }
                        
                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                        printThreadingNotes("DRAG DROPPED ON GRIDPANE HANDLED");
                    }
                }
                event.setDropCompleted(true);
                // consuming prevents the propagation of the event to the anchorPaneRoot (as a sub-node of anchorPaneRoot, GridPane is prioritized)
                // https://openjfx.io/javadoc/11/javafx.base/javafx/event/Event.html#consume()
                // to understand this in full detail, ask your tutor or read https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm
                event.consume();
            }
        });

        // this doesn't fire when we drag over GridPane because in the event handler for dragging over GridPanes, we consume the event
        anchorPaneRootSetOnDragOver.put(draggableType, new EventHandler<DragEvent>(){
            // https://github.com/joelgraff/java_fx_node_link_demo/blob/master/Draggable_Node/DraggableNodeDemo/src/application/RootLayout.java#L110
            @Override
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    if(event.getGestureSource() != anchorPaneRoot && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                }
                if (currentlyDraggedType != null){
                    draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                }
                event.consume();
            }
        });

        // this doesn't fire when we drop over GridPane because in the event handler for dropping over GridPanes, we consume the event
        anchorPaneRootSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != anchorPaneRoot && db.hasImage()){
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        currentlyDraggedImage.setVisible(true);
                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        removeDraggableDragEventHandlers(draggableType, targetGridPane);
                        
                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                    }
                }
                //let the source know whether the image was successfully transferred and used
                event.setDropCompleted(true);
                event.consume();
            }
        });
    }

    /**
     * remove the card from the world, and spawn and return a building instead where the card was dropped
     * @param cardNodeX the x coordinate of the card which was dragged, from 0 to width-1
     * @param cardNodeY the y coordinate of the card which was dragged (in starter code this is 0 as only 1 row of cards)
     * @param buildingNodeX the x coordinate of the drop location for the card, where the building will spawn, from 0 to width-1
     * @param buildingNodeY the y coordinate of the drop location for the card, where the building will spawn, from 0 to height-1
     * @return building entity returned from the world
     */
    private Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        return world.convertCardToBuildingByCoordinates(cardNodeX, cardNodeY, buildingNodeX, buildingNodeY);
    }

    /**
     * add drag event handlers to an ImageView
     * @param view the view to attach drag event handlers to
     * @param draggableType the type of item being dragged - card or item
     * @param sourceGridPane the relevant gridpane from which the entity would be dragged
     * @param targetGridPane the relevant gridpane to which the entity would be dragged to
     */
    private void addDragEventHandlers(ImageView view, DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane){
        view.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                currentlyDraggedImage = view; // set image currently being dragged, so squares setOnDragEntered can detect it...
                currentlyDraggedType = draggableType;
                //Drag was detected, start drap-and-drop gesture
                //Allow any transfer node
                Dragboard db = view.startDragAndDrop(TransferMode.MOVE);
    
                //Put ImageView on dragboard
                ClipboardContent cbContent = new ClipboardContent();
                cbContent.putImage(view.getImage());
                db.setContent(cbContent);
                view.setVisible(false);

                buildNonEntityDragHandlers(draggableType, sourceGridPane, targetGridPane);

                draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                switch (draggableType){
                    case CARD:
                        if (view.getImage().equals(vampireCastleCardImage)) {
                            draggedEntity.setImage(vampireCastleCardImage);
                        }
                        if (view.getImage().equals(zombiePitCardImage)) {
                            draggedEntity.setImage(zombiePitCardImage);
                        }
                        if (view.getImage().equals(towerCardImage)) {
                            draggedEntity.setImage(towerCardImage);
                        }
                        if (view.getImage().equals(towerCardImage)) {
                            draggedEntity.setImage(towerCardImage);
                        }
                        if (view.getImage().equals(villageCardImage)) {
                            draggedEntity.setImage(villageCardImage);
                        }
                        if (view.getImage().equals(barracksCardImage)) {
                            draggedEntity.setImage(barracksCardImage);
                        }
                        if (view.getImage().equals(trapCardImage)) {
                            draggedEntity.setImage(trapCardImage);
                        }
                        if (view.getImage().equals(campfireCardImage)) {
                            draggedEntity.setImage(campfireCardImage);
                        }
                        break;
                    case ITEM:
                        if (view.getImage().equals(swordImage)) {
                            draggedEntity.setImage(swordImage);
                        }
                        if (view.getImage().equals(staffImage)) {
                            draggedEntity.setImage(staffImage);
                        }
                        if (view.getImage().equals(stakeImage)) {
                            draggedEntity.setImage(stakeImage);
                        }
                        if (view.getImage().equals(armourImage)) {
                            draggedEntity.setImage(armourImage);
                        }
                        if (view.getImage().equals(helmetImage)) {
                            draggedEntity.setImage(helmetImage);
                        }
                        if (view.getImage().equals(shieldImage)) {
                            draggedEntity.setImage(shieldImage);
                        }
                        if (view.getImage().equals(healthPotionImage)) {
                            draggedEntity.setImage(healthPotionImage);
                        }
                        break;
                    default:
                        break;
                }
                
                draggedEntity.setVisible(true);
                draggedEntity.setMouseTransparent(true);
                draggedEntity.toFront();

                // IMPORTANT!!!
                // to be able to remove event handlers, need to use addEventHandler
                // https://stackoverflow.com/a/67283792
                targetGridPane.addEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

                for (Node n: targetGridPane.getChildren()){
                    // events for entering and exiting are attached to squares children because that impacts opacity change
                    // these do not affect visibility of original image...
                    // https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
                    gridPaneNodeSetOnDragEntered.put(draggableType, new EventHandler<DragEvent>() {
                        // TODO = be more selective about whether highlighting changes - if it cannot be dropped in the location, the location shouldn't be highlighted!
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType){
                            //The drag-and-drop gesture entered the target
                            //show the user that it is an actual gesture target
                                if(event.getGestureSource() != n && event.getDragboard().hasImage()){
                                    n.setOpacity(0.7);
                                }
                            }
                            event.consume();
                        }
                    });
                    gridPaneNodeSetOnDragExited.put(draggableType, new EventHandler<DragEvent>() {
                        // TODO = since being more selective about whether highlighting changes, you could program the game so if the new highlight location is invalid the highlighting doesn't change, or leave this as-is
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType){
                                n.setOpacity(1);
                            }
                
                            event.consume();
                        }
                    });
                    n.addEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
                    n.addEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
                }
                event.consume();
            }
            
        });
    }

    /**
     * remove drag event handlers so that we don't process redundant events
     * this is particularly important for slower machines such as over VLAB.
     * @param draggableType either cards, or items in unequipped inventory
     * @param targetGridPane the gridpane to remove the drag event handlers from
     */
    private void removeDraggableDragEventHandlers(DRAGGABLE_TYPE draggableType, GridPane targetGridPane){
        // remove event handlers from nodes in children squares, from anchorPaneRoot, and squares
        targetGridPane.removeEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));

        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

        for (Node n: targetGridPane.getChildren()){
            n.removeEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
            n.removeEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
        }
    }

    /**
     * handle the pressing of keyboard keys.
     * Specifically, we should pause when pressing SPACE
     * @param event some keyboard key press
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        // TODO = handle additional key presses, e.g. for consuming a health potion
        switch (event.getCode()) {
        case SPACE:
            if (isPaused){
                startTimer();
            }
            else{
                pause();
            }    
            break;
        case ENTER:
            if (isPaused) {
                world.useHealthPotion();
                isPaused =false;
            } else {
                isPaused =true;
            }
            break;
        default:
            break;
        }
        
    }

    public void setMainMenuSwitcher(MenuSwitcher mainMenuSwitcher){
        // TODO = possibly set other menu switchers
        this.mainMenuSwitcher = mainMenuSwitcher;
    }

    public void setShopMenuSwitcher(MenuSwitcher shopMenuSwitcher){
        // TODO = possibly set other menu switchers
        this.shopMenuSwitcher = shopMenuSwitcher;
    }

    /**
     * this method is triggered when click button to go to main menu in FXML
     * @throws IOException
     */
    @FXML
    private void switchToMainMenu() throws IOException {
        // TODO = possibly set other menu switchers
        pause();
        mainMenuSwitcher.switchMenu();
    }


    @FXML
    private void switchToShopMenu() throws IOException {
        pause();
        shopMenuSwitcher.switchMenu();
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the world.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * 
     * note that this is put in the controller rather than the loader because we need to track positions of spawned entities such as enemy
     * or items which might need to be removed should be tracked here
     * 
     * NOTE teardown functions setup here also remove nodes from their GridPane. So it is vital this is handled in this Controller class
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        // TODO = tweak this slightly to remove items from the equipped inventory?
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());

        ChangeListener<Number> xListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        };
        ChangeListener<Number> yListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        };

        // if need to remove items from the equipped inventory, add code to remove from equipped inventory gridpane in the .onDetach part
        ListenerHandle handleX = ListenerHandles.createFor(entity.x(), node)
                                               .onAttach((o, l) -> o.addListener(xListener))
                                               .onDetach((o, l) -> {
                                                    o.removeListener(xListener);
                                                    entityImages.remove(node);
                                                    squares.getChildren().remove(node);
                                                    cards.getChildren().remove(node);
                                                    equippedItems.getChildren().remove(node);
                                                    unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        ListenerHandle handleY = ListenerHandles.createFor(entity.y(), node)
                                               .onAttach((o, l) -> o.addListener(yListener))
                                               .onDetach((o, l) -> {
                                                   o.removeListener(yListener);
                                                   entityImages.remove(node);
                                                   squares.getChildren().remove(node);
                                                   cards.getChildren().remove(node);
                                                   equippedItems.getChildren().remove(node);
                                                   unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        handleX.attach();
        handleY.attach();

        // this means that if we change boolean property in an entity tracked from here, position will stop being tracked
        // this wont work on character/path entities loaded from loader classes
        entity.shouldExist().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> obervable, Boolean oldValue, Boolean newValue) {
                handleX.detach();
                handleY.detach();
            }
        });
    }

    /**
     * we added this method to help with debugging so you could check your code is running on the application thread.
     * By running everything on the application thread, you will not need to worry about implementing locks, which is outside the scope of the course.
     * Always writing code running on the application thread will make the project easier, as long as you are not running time-consuming tasks.
     * We recommend only running code on the application thread, by using Timelines when you want to run multiple processes at once.
     * EventHandlers will run on the application thread.
     */
    private void printThreadingNotes(String currentMethodLabel){
        System.out.println("\n###########################################");
        System.out.println("current method = "+currentMethodLabel);
        System.out.println("In application thread? = "+Platform.isFxApplicationThread());
        System.out.println("Current system time = "+java.time.LocalDateTime.now().toString().replace('T', ' '));
    }
}
