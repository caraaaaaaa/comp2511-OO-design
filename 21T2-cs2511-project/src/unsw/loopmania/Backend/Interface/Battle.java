package unsw.loopmania.Backend.Interface;

import java.util.List;

import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Enemy.BasicEnemy;

public interface Battle {
    void battle(LoopManiaWorld world, List<BasicEnemy> defeatedEnemies);
}
