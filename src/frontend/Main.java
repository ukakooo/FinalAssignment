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
                int choice = sigmaSkibidi.nextInt();
                System.out.println();
                switch (choice) {
                    case 1:
                        manageGenre();
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
                sigmaSkibidi.nextLine();
            }
        }
    }

    public void manageGenre() {
        String mainMenuText = "================= Manage Genre ====================\n" +
                "1. Insert new Genre\n" +
                "2. Update Genre\n" +
                "3. Delete Genre\n" +
                "0. Back to Main Menu\n" +
                "===================================================\n" +
                "Choose Menu: ";
        try {
            System.out.print(mainMenuText);
            int choice = sigmaSkibidi.nextInt();
            sigmaSkibidi.nextLine();
            Genre genre;
            ArrayList<Genre> listGenre = new Genre().getAll();

            switch (choice) {
                case 1:
                    System.out.print("Input Genre Name: ");
                    String genreName = sigmaSkibidi.nextLine();
                    System.out.print("Input Genre Description: ");
                    String genreDesc = sigmaSkibidi.nextLine();
                    genre = new Genre(genreName, genreDesc);
                    genre.save();
                    listGenre = genre.getAll();
                    System.out.println("New Genre added successfully!");
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Description");
                    System.out.println("==================================================================");
                    if (!listGenre.isEmpty()) {
                        Genre lastGenre = listGenre.get(listGenre.size() - 1);
                        System.out.printf("%-5d %-25s %-20s\n", lastGenre.getIdGenre(), lastGenre.getGenreName(),
                                lastGenre.getGenreDesc());
                    }
                    System.out.println("==================================================================");
                    break;

                case 2:
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Description");
                    System.out.println("==================================================================");
                    for (Genre g : listGenre) {
                        System.out.printf("%-5d %-25s %-20s\n", g.getIdGenre(), g.getGenreName(), g.getGenreDesc());
                    }
                    System.out.println("==================================================================");
                    System.out.println("");
                    System.out.print("Enter the Genre ID: ");
                    int genreId = sigmaSkibidi.nextInt();
                    sigmaSkibidi.nextLine();

                    System.out.print("Input Genre Name: ");
                    String genreNameUpd = sigmaSkibidi.nextLine();
                    System.out.print("Input Genre Description: ");
                    String genreDescUpd = sigmaSkibidi.nextLine();
                    genre = new Genre(genreNameUpd, genreDescUpd);
                    genre.setIdGenre(genreId);
                    genre.save();
                    listGenre = genre.getAll();
                    System.out.println("Genre updated successfully!");

                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Description");
                    System.out.println("==================================================================");
                    if (!listGenre.isEmpty()) {
                        Genre getUpdGenre = listGenre.get(genreId - 1);
                        System.out.printf("%-5d %-25s %-20s\n", getUpdGenre.getIdGenre(), getUpdGenre.getGenreName(),
                                getUpdGenre.getGenreDesc());
                    }
                    break;
                case 3:
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Description");
                    System.out.println("==================================================================");
                    for (Genre g : listGenre) {
                        System.out.printf("%-5d %-25s %-20s\n", g.getIdGenre(), g.getGenreName(), g.getGenreDesc());
                    }
                    System.out.println("==================================================================");
                    System.out.print("Enter the Genre ID: ");
                    int genreIdDel = sigmaSkibidi.nextInt();
                    sigmaSkibidi.nextLine();
                    genre = new Genre();
                    genre.setIdGenre(genreIdDel);
                    genre.delete();
                    System.out.println("Genre deleted successfully!");
                    break;
                case 0:
                    return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input, buddy.");
            sigmaSkibidi.nextLine();
            System.out.println();
            this.manageGenre();
        }
    }
}
