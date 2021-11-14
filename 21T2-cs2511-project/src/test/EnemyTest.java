package test;

import org.junit.Test;
import unsw.loopmania.*;
import unsw.loopmania.Backend.LoopManiaWorld;
import unsw.loopmania.Backend.Building.VampireCastleBuilding;
import unsw.loopmania.Backend.Enemy.BasicEnemy;
import unsw.loopmania.Backend.Enemy.Doggie;
import unsw.loopmania.Backend.Enemy.Muske;
import unsw.loopmania.Backend.Enemy.Slug;
import unsw.loopmania.Backend.Enemy.Vampire;
import unsw.loopmania.Backend.Enemy.Zombie;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;
import static test.TrivialTest.loadWorld;

public class EnemyTest {
    static Class<?>[] enemyClasses = {
            Zombie.class, Vampire.class, Slug.class, Doggie.class, Muske.class,
    };

    @Test
    public void testSlug() {
        var world = loadWorld();
        Slug enemy = new Slug(new PathPosition(0, world.getOrderedPath()));
        world.getEnemies().add(enemy);
        assertEquals(world.getEnemies().get(0).getAttackValue(), 10, 0.1);
        assertEquals(world.getEnemies().get(0).getHealthPoints(), 20);
    }

    @Test
    public void testZombie() {
        var world = loadWorld();
        Zombie enemy = new Zombie(new PathPosition(0, world.getOrderedPath()));
        world.getEnemies().add(enemy);
        // assertEquals(world.getEnemies().get(0).getAttackValue(), 20);
        assertEquals(world.getEnemies().get(0).getHealthPoints(), 50);
    }

    @Test
    public void testVampire() {
        var world = loadWorld();
        Vampire enemy = new Vampire(new PathPosition(0, world.getOrderedPath()));
        world.getEnemies().add(enemy);
        // assertEquals(world.getEnemies().get(0).getAttackValue(), 40, 0.1);
        assertEquals(world.getEnemies().get(0).getHealthPoints(), 50);
    }

    @Test
    public void testEnemyMethods() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        LoopManiaWorld world = loadWorld();

        for (Class<?> clazz : enemyClasses) {
            BasicEnemy enemy = (BasicEnemy) clazz.getConstructor(PathPosition.class).newInstance(new PathPosition(0, world.getOrderedPath()));
            Field attackField = clazz.getDeclaredField("attackValue");
            attackField.setAccessible(true);
            assertEquals(enemy.getAttackValue(), attackField.getDouble(enemy), 1e-6);
            double oldAttack = enemy.getAttackValue();
            enemy.updataAttackValue(1);
            assertEquals(enemy.getAttackValue(), oldAttack + 1, 1e-6);

            Field hp = clazz.getDeclaredField("healthpoints");
            hp.setAccessible(true);
            assertEquals(enemy.getHealthPoints(), hp.getInt(enemy));

            Field criticalProbField = clazz.getDeclaredField("criticalAttackProbability");
            criticalProbField.setAccessible(true);
            assertEquals(enemy.getCriticalAttackProbabilit(), criticalProbField.getDouble(enemy), 1e-6);
            enemy.updataCriticalAttackProbabilit(255);
            assertEquals(255, enemy.getCriticalAttackProbabilit(), 1e-6);

        }
    }

    @Test
    public void testSpawnEnemies() {
        LoopManiaWorld world = loadWorld();
        var list = world.possiblySpawnEnemies();
        assertFalse(list.isEmpty());

        world.setRound(40);
        world.updateExpAmount(20000);
        VampireCastleBuilding building = new VampireCastleBuilding(0, 1);

        world.getBuildings().add(building);
        var list2 = world.possiblySpawnEnemies();
        assertTrue(list2.size() > list.size());
    }
}
