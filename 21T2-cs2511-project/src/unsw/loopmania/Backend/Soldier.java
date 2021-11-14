package unsw.loopmania.Backend;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Backend.Entity.StaticEntity;

public class Soldier extends StaticEntity{
    private double HP = 2;
    private double damage = 2;

    public Soldier(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double hP) {
        this.HP = hP;
    }
    
}
