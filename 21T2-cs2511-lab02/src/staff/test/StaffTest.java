package staff.test;

import staff.Lecturer;
import staff.StaffMember;

import java.time.LocalDate;

public class StaffTest {

    public void printStaffDetails(StaffMember staff) {
        System.out.println(staff);
    }

    public static void main(String[] args) {

        StaffMember s01 = new StaffMember("Cara", 100.0, LocalDate.of(2020, 1, 1),LocalDate.of(2020, 12, 1));
        StaffMember s02 = new StaffMember("Leo", 10.0, LocalDate.of(2019, 1, 1),LocalDate.of(2020, 12, 1));

        Lecturer l01 = new Lecturer("Cara", 100.33, LocalDate.of(2020, 1, 1),LocalDate.of(2020, 12, 1), "UNSW", 'S');
        Lecturer l02 = new Lecturer("Leo", 11.5, LocalDate.of(2019, 1, 1),LocalDate.of(2020, 12, 1), "USYD", 'A');

        StaffTest test = new StaffTest();

        System.out.println("------------Staff Details------------");
        test.printStaffDetails(s01);
        test.printStaffDetails(s02);
        test.printStaffDetails(l01);
        test.printStaffDetails(l02);

  
        System.out.println("------------Test Equals---------------");

        if (s01.equals(s01)) System.out.println("CORRECT");
        else System.out.println("ERROR");

        if (s02.equals(s02)) System.out.println("CORRECT");
        else System.out.println("ERROR");

        if (l01.equals(l01)) System.out.println("CORRECT");
        else System.out.println("ERROR");
  
        if (l02.equals(l02)) System.out.println("CORRECT");
        else System.out.println("ERROR");

        if (s01.equals(s02)) System.out.println("ERROR");
        else System.out.println("CORRECT");

        if (s01.equals(l01)) System.out.println("ERROR");
        else System.out.println("CORRECT");

        if (s01.equals(null)) System.out.println("ERROR");
        else System.out.println("CORRECT");

        if (s02.equals(null)) System.out.println("ERROR");
        else System.out.println("CORRECT");

        if (l01.equals(null)) System.out.println("ERROR");
        else System.out.println("CORRECT");

        if (l02.equals(null)) System.out.println("ERROR");
        else System.out.println("CORRECT");

    }
    
}