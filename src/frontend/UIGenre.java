package frontend;

import java.util.ArrayList;
import java.util.InputMismatchException;

import backend.*;

public class UIGenre {

    public void manageGenre() {
        String mainMenuText = "================= Manage Genre ====================\n" +
                "1. Insert new Genre\n" +
                "2. Update Genre\n" +
                "3. Delete Genre\n" +
                "4. Show Tables\n" +
                "0. Back to Main Menu\n" +
                "===================================================\n" +
                "Choose Menu: ";
        try {
            System.out.print(mainMenuText);
            int choice = Main.sigmaSkibidi.nextInt();
            Main.sigmaSkibidi.nextLine();

            switch (choice) {
                case 1:
                    addGenre();
                    break;

                case 2:
                    updateGenre();
                    break;
                case 3:
                    deleteGenre();
                    break;
                case 4:
                    showTables();
                    break;
                case 0:
                    return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input, buddy.");
            Main.sigmaSkibidi.nextLine();
            System.out.println();
            this.manageGenre();
        }
    }

    public void addGenre() {
        ArrayList<Genre> listGenre = new Genre().getAll();
        Genre genre;
        System.out.print("Input Genre Name: ");
        String genreName = Main.sigmaSkibidi.nextLine();
        System.out.print("Input Genre Description: ");
        String genreDesc = Main.sigmaSkibidi.nextLine();
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
    }

    public void updateGenre() {
        ArrayList<Genre> listGenre = new Genre().getAll();
        Genre genre;
        System.out.println("==================================================================");
        System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Description");
        System.out.println("==================================================================");
        for (Genre g : listGenre) {
            System.out.printf("%-5d %-25s %-20s\n", g.getIdGenre(), g.getGenreName(), g.getGenreDesc());
        }
        System.out.println("==================================================================");
        System.out.println("");
        System.out.print("Enter the Genre ID: ");
        int genreId = Main.sigmaSkibidi.nextInt();
        Main.sigmaSkibidi.nextLine();
        Genre oldGenreData = new Genre().getByID(genreId);

        if (oldGenreData.getIdGenre() == 0) {
            System.out.println("Put a proper ID, buddy.");
            return;
        }

        System.out.print("Input Genre Name (Press Enter to keep unchanged): ");
        String genreNameUpd = Main.sigmaSkibidi.nextLine();
        if (genreNameUpd.isEmpty()) {
            genreNameUpd = oldGenreData.getGenreName();
        }
        System.out.print("Input Genre Description (Press Enter to keep unchanged): ");
        String genreDescUpd = Main.sigmaSkibidi.nextLine();
        if (genreDescUpd.isEmpty()) {
            genreDescUpd = oldGenreData.getGenreDesc();
        }
        genre = new Genre(genreNameUpd, genreDescUpd);
        genre.setIdGenre(genreId);
        genre.save();
        listGenre = genre.getAll();
        System.out.println("Genre updated successfully!");

        System.out.println("==================================================================");
        System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Description");
        System.out.println("==================================================================");
        if (!listGenre.isEmpty()) {
            System.out.printf("%-5d %-25s %-20s\n", genre.getIdGenre(), genre.getGenreName(),
                    genre.getGenreDesc());
        }
        System.out.println("==================================================================");
    }

    public void deleteGenre() {
        ArrayList<Genre> listGenre = new Genre().getAll();
        Genre genre;
        System.out.println("==================================================================");
        System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Description");
        System.out.println("==================================================================");
        for (Genre g : listGenre) {
            System.out.printf("%-5d %-25s %-20s\n", g.getIdGenre(), g.getGenreName(), g.getGenreDesc());
        }
        System.out.println("==================================================================");
        System.out.print("Enter the Genre ID: ");
        int genreIdDel = Main.sigmaSkibidi.nextInt();

        Genre targetGenre = new Genre().getByID(genreIdDel);

        if (targetGenre == null) {
            System.out.println("Put a proper ID, buddy.");
            return;
        }

        Main.sigmaSkibidi.nextLine();
        genre = new Genre();
        genre.setIdGenre(genreIdDel);
        genre.delete();
        System.out.println("Genre deleted successfully!");
    }

    public void showTables() {
        ArrayList<Genre> listGenre = new Genre().getAll();
        System.out.println("==================================================================");
        System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Description");
        System.out.println("==================================================================");
        for (Genre g : listGenre) {
            System.out.printf("%-5d %-25s %-20s\n", g.getIdGenre(), g.getGenreName(), g.getGenreDesc());
        }
        System.out.println("==================================================================");
    }
}
