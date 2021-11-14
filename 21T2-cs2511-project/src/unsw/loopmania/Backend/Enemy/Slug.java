package unsw.loopmania.Backend.Enemy;

import java.util.List;
import java.util.Random;

import unsw.loopmania.PathPosition;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Character.Character;
import unsw.loopmania.Backend.Entity.Entity;
import unsw.loopmania.Backend.Item.Item;

/**
 * a basic form of enemy in the world
 */
public class Slug extends BasicEnemy{

    private int healthpoints;
    private double attackValue;
    private int attackRadius = 1;
    private double criticalAttackProbability = Math.random();

    // TODO = modify this, and add additional forms of enemy
    public Slug(PathPosition position) {
        super(position);
        healthpoints = 20;
        attackValue = 10;
    }

    /**
     * move the enemy
     */
    public void move(){
        // TODO = modify this, since this implementation doesn't provide the expected enemy behaviour
        // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
        int directionChoice = (new Random()).nextInt(5);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }

    @Override
    public int getHealthPoints() {
        // TODO Auto-generated method stub
        return healthpoints;
    }

    @Override
    public void damage(double points) {
        // TODO Auto-generated method stub
        healthpoints -= points;
    }

    @Override
    public double getAttackValue() {
        // TODO Auto-generated method stub
        return attackValue;
    }

    @Override
    public void updataAttackValue(double points) {
        // TODO Auto-generated method stub
        this.attackValue += points;
    }

    @Override
    public double getCriticalAttackProbabilit() {
        // TODO Auto-generated method stub
        return criticalAttackProbability;
    }

    @Override
    public void updataCriticalAttackProbabilit(double value) {
        // TODO Auto-generated method stub
        this.criticalAttackProbability = value;
    }

    @Override
    public void battle(LoopManiaWorld world, List<BasicEnemy> defeatedEnemies) {
        
        // TODO Auto-generated method stub
        Character character = world.getCharacter();
        double damage = character.getAttackValue();
        double defence = character.getDefenceValue();
        for (Item e : world.getEquippedItemsList()){
            if (e.getType().equals("Armour") ){
                this.attackValue = attackValue/2;
            }
        }
        if (Entity.getDistance(character, this) <= attackRadius) {
            if (defence < this.getAttackValue()) {
                if ((character.getHealthPoints() / (this.getAttackValue() - defence)) > (this.getHealthPoints() / damage)) {
                    int times = (int)(this.getHealthPoints() / damage);
                    if ((this.getHealthPoints() / damage) < 1) {
                        times = 1;
                    }
                    if (world.getSoldierList().size() > 0) {
                        world.getSoldierList().remove(0);
                    } else {
                        character.setHealthpoints((int)(character.getHealthPoints() - times * (this.getAttackValue() - defence)));;
                    }
                    defeatedEnemies.add(this);
                } else {
                    character.setHealthpoints(0);
                }
            } else {
                defeatedEnemies.add(this);
            }
        }

    }

    @Override
    public double getDefenceValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void updataDefenceValue(double d) {
        // TODO Auto-generated method stub
        
    }
}
