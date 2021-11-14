package unsw.loopmania.Backend.Interface;

public interface DamageAttackUpdate {

    public int getHealthPoints();

    public void damage(double points);

    public double getCriticalAttackProbabilit();

    public void updataCriticalAttackProbabilit(double value);

    public double getAttackValue();

    public void updataAttackValue(double d);

    public double getDefenceValue();

    public void updataDefenceValue(double d);
}
