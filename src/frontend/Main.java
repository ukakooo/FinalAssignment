package frontend;

import backend.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.sql.*;
import java.util.Scanner;

public class Main {

    static Scanner sigmaSkibidi = new Scanner(System.in);

    public static void main(String[] args) {
        // Main Menu
        Main main = new Main();
        UIGenre frontGenre = new UIGenre();
        main.mainMenu();
    }

    public void mainMenu() {
        while (true) {
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
            try {
                System.out.println();
                System.out.print(mainMenuText);
                int choice = Main.sigmaSkibidi.nextInt();
                System.out.println();
                switch (choice) {
                    case 1:
                        UIGenre frontGenre = new UIGenre(); frontGenre.manageGenre();
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
                    default:
                        System.out.println("Wrong input, buddy.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, buddy.");
                Main.sigmaSkibidi.nextLine();
            }
        }
    }

}
