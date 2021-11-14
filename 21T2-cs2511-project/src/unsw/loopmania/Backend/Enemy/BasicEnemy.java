package unsw.loopmania.Backend.Enemy;

import java.util.Random;

import unsw.loopmania.PathPosition;
import unsw.loopmania.Backend.Entity.MovingEntity;
import unsw.loopmania.Backend.Interface.Battle;
import unsw.loopmania.Backend.Interface.DamageAttackUpdate;

/**
 * a basic form of enemy in the world
 */
public abstract class BasicEnemy extends MovingEntity implements DamageAttackUpdate, Battle{

    // TODO = modify this, and add additional forms of enemy
    public BasicEnemy(PathPosition position) {
        super(position);
    }

    public void move(){
        // TODO = modify this, since this implementation doesn't provide the expected enemy behaviour
        // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }
}

