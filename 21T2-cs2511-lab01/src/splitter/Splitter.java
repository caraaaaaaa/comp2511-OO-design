package splitter;

import java.util.Scanner;

public class Splitter {

    public static void main(String[] args) {
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for (String string: input.split(" ")) {
            System.out.println(string);
        }
        scanner.close();
    }
}
