package programmingexample7;

public class Engineer extends Employee {

    private double bonus;

    public Engineer(String title, String firstName, String lastName, double bonus) {
        super(title, firstName, lastName);
        this.bonus = bonus;
    }

    @Override
    public double getBonus() {
        return bonus;
    }
}
