package staff;

import java.time.LocalDate;

public class Lecturer extends StaffMember {
    private String school;
    private char status;

    public Lecturer(String name, Double salary, LocalDate hireDate, LocalDate endDate, String school, char status) {
        super(name,salary,hireDate,endDate);
        this.school = school;
        this.status = status;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override 
    public String toString() {
        String strSchool = "School: " + this.school;
        String strLevel = "Academic Level: " + this.status;
        return super.toString() + " " + strSchool + " " + strLevel;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;

        Lecturer l = (Lecturer) obj;
        return (l.getSchool() == this.getSchool()) && (l.getStatus() == this.getStatus());
    }
}
