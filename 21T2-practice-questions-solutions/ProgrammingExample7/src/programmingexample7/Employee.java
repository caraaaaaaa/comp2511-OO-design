package programmingexample7;

public abstract class Employee {
    private String title;
    private String firstName;
    private String lastName;
    private double baseSalary;

    // Code Smell:  RefusedBequest - salesTarget and salesAchieved were relevant only to sub-class SalesPerson
    // Used Refactoring technique Push Down Field and Push Down Method to move field and behaviour to SalesPerson

    public Employee (String title, String firstName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double calculateTax() {
        double tax = 0;
        double salary = baseSalary;
        if (salary > 50000) {
            tax += 0.3*(salary - 50000);
        }
        if (salary > 30000) {
            tax += 0.2*(salary - 30000);
        }
        return tax;
    }

    public double calculateParkingFringeBenefits() {
        return (baseSalary - 50000) / 10000;
    }

    // Code smell: duplicate code
    // This method was in both Engineer and SalesPerson. Used Pull up Method to
    // move method to abstract class Employee
    public double calculateSalary() {
        double totalSal;
        totalSal = this.getBaseSalary() + getBonus()
                 + this.calculateParkingFringeBenefits() - this.calculateTax();
        return totalSal;
    }

    public abstract double getBonus();


}
