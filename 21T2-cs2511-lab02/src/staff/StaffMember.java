package staff;

import java.time.LocalDate;

/**
 * A staff member
 * @author Robert Clifton-Everest
 *
 */
public class StaffMember {
    private String name;
    private Double salary;
    private LocalDate hireDate;
    private LocalDate endDate;

    public StaffMember(String name, Double salary, LocalDate hireDate, LocalDate endDate) {
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        String strName = "Staff name: " + this.name;
        String strHire = "Hired from " + this.hireDate + " to " + this.endDate;
        String strSalary = "Salary: $" + this.salary;
        return strName + " " + strHire + " " + strSalary;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        
        if (this.getClass() != obj.getClass()) return false;

        StaffMember staff = (StaffMember) obj;
        return (this.name.equals(staff.name) && this.salary == staff.salary);
    }

}
