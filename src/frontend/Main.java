package frontend;

import backend.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.Scanner;

public class Main {

    static Scanner sigmaSkibidi = new Scanner(System.in);
    public static void main(String[] args) {
        // Main Menu
        Main main = new Main();
        main.mainMenu();
    }

    public void mainMenu() {
        while(true) {
            String mainMenuText = "================= MAIN MENU =====================\n" +
                               "1. Manage Genre\n" +
                               "2. Manage Game\n" +
                               "3. Manage Customer\n" +
                               "4. Manage Renting\n" +
                               "5. Manage Purchase\n" +
                               "6. Count Total Payment for each Customer\n" +
                               "7. Count Total Payment for each Game\n" +
                               "0. Exit\n" +
                                  "==================================================\n" +
                               "Choose Menu: ";
            System.out.print(mainMenuText);
            int choice = sigmaSkibidi.nextInt();
            System.out.println();
            switch (choice) {
                case 1: 
                    Genre gen = new Genre();
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Description");
                    System.out.println("==================================================================");
                    ArrayList<Genre> listGenre = gen.getAll();
                    for (Genre g : listGenre) {
                        System.out.printf("%-5d %-25s %-20s\n", g.getIdGenre(), g.getGenreName(), g.getGenreDesc());
                    }
                    System.out.println("==================================================================");

                    System.out.println("");
                    System.out.println("Enter the Genre ID: ");
                    // manageGenre(); 
                    break;
                // case 2: manageGame(); break;
                // case 3: manageCustomer(); break;
                // case 4: manageRenting(); break;
                // case 5: managePurchase(); break;
                // case 6: countTotalPaymentForCustomer(); break;
                // case 7: countTotalPaymentForGame(); break;
                case 0:
                    System.out.println("Goodbye!"); 
                    System.exit(0);
            }
        }
    }
}
