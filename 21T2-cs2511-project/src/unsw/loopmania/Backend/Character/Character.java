package unsw.loopmania.Backend.Character;

import unsw.loopmania.PathPosition;
import unsw.loopmania.Backend.Entity.MovingEntity;
import unsw.loopmania.Backend.Interface.DamageAttackUpdate;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements DamageAttackUpdate{
    // TODO = potentially implement relationships between this class and other classes
    private int healthpoints;
    private double attackValue;
    private double defenceValue;
    private double criticalAttackProbability = Math.random();
    static Character character = null;

    public static Character getInstance() {
        assert character != null;
        return character;
    }

    public Character(PathPosition position) {
        super(position);
        healthpoints = 1000;
        attackValue = 20;
        defenceValue = 0;
        Character.character = this;
    }

    public void fillHealthPoints() {
        healthpoints = 1000;
    }

    @Override
    public int getHealthPoints() {
        // TODO Auto-generated method stub
        return healthpoints;
    }

    @Override
    public void damage(double points){
        healthpoints -= points;
    }

    @Override
    public double getAttackValue() {
        return attackValue;
    }

    @Override
    public void updataAttackValue(double d) {
        // TODO Auto-generated method stub
        this.attackValue += d;
    }

    @Override
    public double getCriticalAttackProbabilit() {
        // TODO Auto-generated method stub
        return criticalAttackProbability;
    }

    @Override
    public void updataCriticalAttackProbabilit(double value) {
        // TODO Auto-generated method stub
        criticalAttackProbability = value;
    }

    @Override
    public double getDefenceValue() {
        // TODO Auto-generated method stub
        return defenceValue;
    }

    @Override
    public void updataDefenceValue(double d) {
        // TODO Auto-generated method stub
        this.defenceValue += d;
    }

    public void setHealthpoints(int healthpoints) {
        this.healthpoints = healthpoints;
    }
}

