package application;

import java.util.Scanner;
import util.Library;

public class LibraryMenuApp {
    private static Library library = new Library(); //the library object has member and books lists
    private static Scanner scan = new Scanner(System.in);

    //main method
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== LIBRARY MENU ===");
            System.out.println("1. Book - Add Book");
            System.out.println("2. Book - Display All Books");
            System.out.println("3. Book - Find Book");
            System.out.println("4. Book - Remove Book");

            System.out.println("5. Member - Add Member");
            System.out.println("6. Member - Display All Members");
            System.out.println("7. Member - Find Member");
            System.out.println("8. Member - Remove Member");

            System.out.println("9. Transaction - Loan Book");
            System.out.println("10. Transaction - Return Book");
            System.out.println("11. File - Save Data");
            System.out.println("12. File - Load Data");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            
            // put in if statement to handle input properly to avoid duplication
            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine(); //consume the newline character - this fixes the duplication issue
                System.out.println("");

                switch (choice) {
                    case 1 -> library.addBook();
                    case 2 -> library.displayAllBooks();
                    case 3 -> library.findBook();
                    case 4 -> library.removeBook();

                    case 5 -> library.addMember();
                    case 6 -> library.displayAllMembers();
                    case 7 -> library.findMember();
                    case 8 -> library.removeMember();

                    case 9 -> library.loanBook();
                    case 10 -> library.returnBook();
                    case 11 -> library.saveAll();
                    case 12 -> library.loadAll();
                    case 0 -> {
                        running = false;
                        System.out.println("Exiting...");
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } else {
                //if non-integer, clear the invalid input and display error
                scan.nextLine();
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        scan.close();
    }
}