package frontend;

import java.util.ArrayList;
import java.util.InputMismatchException;

import backend.*;

public class UIGame extends UserInterface {
    public void manageGame() {
        String mainMenuText = "================= Manage Game ====================\n" +
                "1. Insert new Game\n" +
                "2. Update Game\n" +
                "3. Delete Game\n" +
                "4. Show Tables\n" +
                "0. Back to Main Menu\n" +
                "==================================================\n" +
                "Choose Menu: ";
        try {
            System.out.print(mainMenuText);
            int choice = Main.sigmaSkibidi.nextInt();
            Main.sigmaSkibidi.nextLine();

            switch (choice) {
                case 1:
                    insert();
                    break;

                case 2:
                    update();
                    break;
                case 3:
                    delete();
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
            this.manageGame();
        }
    }

    @Override
    public void insert() {
        ArrayList<Game> listGame = new ArrayList<Game>();
        Game Game;
        System.out.print("Input Game Title: ");
        String GameName = Main.sigmaSkibidi.nextLine();
        System.out.print("Input ID Genre: ");
        int GameGenre = Main.sigmaSkibidi.nextInt();
        System.out.print("Input buy price: ");
        int GameBuyPrice = Main.sigmaSkibidi.nextInt();
        System.out.print("Input rent price: ");
        int GameRentPrice = Main.sigmaSkibidi.nextInt();
        System.out.print("Input Publisher: ");
        Main.sigmaSkibidi.nextLine();
        String GamePublisher = Main.sigmaSkibidi.nextLine();
        System.out.print("Input Studio: ");
        String GameStudio = Main.sigmaSkibidi.nextLine();
        Genre selectedGenre = new Genre().getByID(GameGenre);
        Game = new Game(GameName, selectedGenre, GameBuyPrice, GameRentPrice, GamePublisher, GameStudio);
        Game.save();
        listGame = Game.getAll();
        System.out.println("New Game added successfully!");
        System.out.println("==================================================================");
        System.out.printf("%-5s %-30s %-25s %-12s %-12s %-25s %-20s\n", "ID", "Name", "Genre", "Buy Price",
                "Rent Price", "Publisher", "Studio");
        System.out.println("==================================================================");
        if (!listGame.isEmpty()) {
            Game lastGame = listGame.get(listGame.size() - 1);
            System.out.printf("%-5s %-30s %-25s %-12s %-12s %-25s %-20s\n", lastGame.getIdGame(),
                    lastGame.getGameTitle(), lastGame.getGenre(), lastGame.getPriceBuy(), lastGame.getPriceRent(),
                    lastGame.getPublisher(), lastGame.getStudio());
        }
        System.out.println("==================================================================");
    }

    @Override
    public void update() {
        ArrayList<Game> listGame = new Game().getAll();
        Game game;
        System.out.println("==================================================================");
        System.out.printf("%-5s %-30s %-25s %-12s %-12s %-25s %-20s\n", "ID", "Name", "Genre", "Buy Price",
                "Rent Price", "Publisher", "Studio");
        System.out.println("==================================================================");
        for (Game g : listGame) {
            System.out.printf("%-5s %-30s %-25s %-12s %-12s %-25s %-20s\n", g.getIdGame(), g.getGameTitle(),
                    g.getGenre().getGenreName(), g.getPriceBuy(), g.getPriceRent(), g.getPublisher(), g.getStudio());
        }
        System.out.println("==================================================================");
        System.out.println("");
        System.out.print("Enter the Game ID: ");
        int GameId = Main.sigmaSkibidi.nextInt();
        Main.sigmaSkibidi.nextLine();
        Game oldGameData = new Game().getByID(GameId);

        if (oldGameData.getIdGame() == 0) {
            System.out.println("Game ID not found in database");
            return;
        }

        System.out.print("Input Game Name (Press Enter to keep unchanged): ");
        String GameNameUpd = Main.sigmaSkibidi.nextLine();
        if (GameNameUpd.isEmpty()) {
            GameNameUpd = oldGameData.getGameTitle();
        }
        System.out.print("Input Game Genre ID (Press Enter to keep unchanged): ");
        String GameGenreUpd = Main.sigmaSkibidi.nextLine();
        Genre selectedGenreUpd;
        if (GameGenreUpd.trim().isEmpty()) {
            selectedGenreUpd = oldGameData.getGenre();
        } else {
            int newGenreId = Integer.parseInt(GameGenreUpd);
            selectedGenreUpd = new Genre().getByID(newGenreId);
        }
        System.out.print("Input Game Buy Price (Press Enter to keep unchanged): ");
        String GameBuyUpd = Main.sigmaSkibidi.nextLine();
        int finalBuyPrice;
        if (GameBuyUpd.trim().isEmpty()) {
            finalBuyPrice = oldGameData.getPriceBuy();
        } else {
            finalBuyPrice = Integer.parseInt(GameBuyUpd);
        }
        System.out.print("Input Game Rent Price (Press Enter to keep unchanged): ");
        String GameRentUpd = Main.sigmaSkibidi.nextLine();
        int finalRentPrice;
        if (GameRentUpd.trim().isEmpty()) {
            finalRentPrice = oldGameData.getPriceRent();
        } else {
            finalRentPrice = Integer.parseInt(GameRentUpd);
        }
        System.out.print("Input Game Publisher (Press Enter to keep unchanged): ");
        String GamePublisherUpd = Main.sigmaSkibidi.nextLine();
        if (GamePublisherUpd.isEmpty()) {
            GamePublisherUpd = oldGameData.getPublisher();
        }
        System.out.print("Input Game Studio (Press Enter to keep unchanged): ");
        String GameStudioUpd = Main.sigmaSkibidi.nextLine();
        if (GameStudioUpd.isEmpty()) {
            GameStudioUpd = oldGameData.getStudio();
        }
        game = new Game(GameNameUpd, selectedGenreUpd, finalBuyPrice, finalRentPrice, GamePublisherUpd, GameStudioUpd);
        game.setIdGame(GameId);
        game.save();

        listGame = game.getAll();
        System.out.println("Game updated successfully!");

        System.out.println("==================================================================");
        System.out.printf("%-5s %-30s %-25s %-12s %-12s %-25s %-20s\n", "ID", "Name", "Genre", "Buy Price",
                "Rent Price", "Publisher", "Studio");
        System.out.println("==================================================================");
        if (!listGame.isEmpty()) {
            System.out.printf("%-5s %-30s %-15s %-12s %-12s %-25s %-20s\n", game.getIdGame(), game.getGameTitle(),
                    game.getGenre().getGenreName(), game.getPriceBuy(), game.getPriceRent(), game.getPublisher(),
                    game.getStudio());
        }
    }

    @Override
    public void delete() {
        ArrayList<Game> listGame = new Game().getAll();
        Game game;
        System.out.println("==================================================================");
        System.out.printf("%-5s %-30s %-25s %-12s %-12s %-25s %-20s\n", "ID", "Name", "Genre ID", "Buy Price",
                "Rent Price", "Publisher", "Studio");
        System.out.println("==================================================================");
        for (Game g : listGame) {
            System.out.printf("%-5s %-30s %-25s %-12s %-12s %-25s %-20s\n", g.getIdGame(), g.getGameTitle(),
                    g.getGenre().getGenreName(), g.getPriceBuy(), g.getPriceRent(), g.getPublisher(), g.getStudio());
        }
        System.out.println("==================================================================");
        System.out.print("Enter the Game ID: ");
        int GameIdDel = Main.sigmaSkibidi.nextInt();
        Main.sigmaSkibidi.nextLine();
        game = new Game();
        game.setIdGame(GameIdDel);
        game.delete();
        System.out.println("Game deleted successfully!");
    }

    public void showTables() {
        ArrayList<Game> listGame = new Game().getAll();
        System.out.println("==================================================================");
        System.out.printf("%-5s %-30s %-25s %-12s %-12s %-25s %-20s\n", "ID", "Name", "Genre", "Buy Price",
                "Rent Price", "Publisher", "Studio");
        System.out.println("==================================================================");
        for (Game g : listGame) {
            System.out.printf("%-5s %-30s %-25s %-12s %-12s %-25s %-20s\n", g.getIdGame(), g.getGameTitle(),
                    g.getGenre().getGenreName(), g.getPriceBuy(), g.getPriceRent(), g.getPublisher(), g.getStudio());
        }
        System.out.println("==================================================================");
    }
}
