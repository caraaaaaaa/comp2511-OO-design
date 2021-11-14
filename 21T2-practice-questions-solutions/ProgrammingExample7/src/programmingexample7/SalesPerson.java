package programmingexample7;

public class SalesPerson extends Employee {

    private float commission;
    private double salesTarget;
    private double salesAchieved;

    public SalesPerson(String title, String firstName, String lastName, int quota) {
        super(title, firstName, lastName);
        this.salesTarget = quota;
    }

    public double calculateSalary() {
        double totalSal;
        totalSal = super.getBaseSalary() + commission * getSalesAchieved()
                 + super.calculateParkingFringeBenefits()
                 - super.calculateTax();
        return totalSal;
    }

    public double getSalesTarget() {
        return salesTarget;
    }

    public double getSalesAchieved() {
        return salesAchieved;
    }

    @Override
    public double getBonus() {
        return commission * getSalesAchieved();
    }

    // Code Smells:  Inappropriate Intimacy and Lazy class
    // getSalesSummary was accessing the methods of class Employee from SalesHistory
    // Once getSalesSummary() is moved, SalesHistory does not do anything, becomes a lazy class
    public String getSalesSummary() {
        return getFirstName() + getLastName() + "Sales Target: " + getSalesTarget() + "$\n" +
                "Sales to date: " + getSalesAchieved() + "$";
    }

}
