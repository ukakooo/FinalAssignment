package frontend;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import backend.*;

public class UIRenting extends UserInterface implements ITransaction {

    @Override
    public void insert() {
        try {
            boolean repeat = true;
            UICustomer frontCustomer = new UICustomer(); frontCustomer.showTables();
            System.out.println("-----------------------------------------");
            System.out.println("Insert New Renting Transaction");
            System.out.println("-----------------------------------------");
            
            System.out.print("Input ID Customer : ");
            int idCustomer = Main.sigmaSkibidi.nextInt();
            Customer cust = new Customer().getByID(idCustomer);
            if (cust.getIdCustomer() == 0) {
                System.out.println("Error: Customer with ID " + idCustomer + " not found!");
                return; 
            }
            
            do {
                    UIGame frontGame = new UIGame(); frontGame.showTables();
                    System.out.print("Input ID Game     : ");
                    int idGame = Main.sigmaSkibidi.nextInt();
                    Game game = new Game().getByID(idGame);
                    if (game.getIdGame() == 0) {
                        System.out.println("Error: Game with ID " + idGame + " not found!");
                        return;
                    }
                    
                    Main.sigmaSkibidi.nextLine(); 
                    
                    LocalDate today = LocalDate.now();
                    String transDate = today.toString(); 
                    String rentDate = today.toString();  
                    
                    System.out.println("Transaction Date : " + transDate + " (Auto)");
                    System.out.println("Rent Date        : " + rentDate + " (Auto)");
                    
                    System.out.print("Input Return Date (YYYY-MM-DD)      : ");
                    String returnDate = Main.sigmaSkibidi.nextLine();
                    
                    LocalDate start = today;
                    LocalDate end = LocalDate.parse(returnDate);
                    
                    long days = ChronoUnit.DAYS.between(start, end);
                    
                    if (days < 0) {
                        System.out.println("Error: Return Date cannot be before Rent Date!");
                        return;
                    }
                    
                    if (days == 0) days = 1; 
                    
                    int pricePerDay = game.getPriceRent(); 
                    int totalPrice = pricePerDay * (int) days;
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Game Price/Day : " + pricePerDay);
                    System.out.println("Duration       : " + days + " day(s)");
                    System.out.println("Total Price    : " + "Rp." + totalPrice + " (Auto Calculated)");
                    System.out.println("-----------------------------------------");

                    Renting renting = new Renting(cust, game, transDate, totalPrice, rentDate, returnDate);
                    renting.save();
                    
                    System.out.println("Transaction saved successfully!");
                    System.out.println("==================================================================");

                    System.out.print("Do you want to add another game? (y/n): ");
                    String answer = Main.sigmaSkibidi.nextLine();
                    if (answer.equalsIgnoreCase("y")) {
                        repeat = true;
                    } else {
                        repeat = false;
                    }
                
                } while (repeat);

            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Invalid date format! Please use YYYY-MM-DD (e.g., 2023-12-31)");
            } catch (Exception e) {
                System.out.println("Error inserting data: " + e.getMessage());
                e.printStackTrace();
        } 
            
    }

    @Override
    public void update() {
        System.out.println("-----------------------------------------");
        System.out.println("Update Renting Transaction");
        System.out.println("-----------------------------------------");
        showTables();

        try {
            System.out.print("Enter ID Renting to Update: ");
            int idRenting = Main.sigmaSkibidi.nextInt();
            Main.sigmaSkibidi.nextLine();

            Renting rent = new Renting().getByID(idRenting);

            if (rent.getIdRenting() == 0) {
                System.out.println("ID Not Found!");
                return;
            }
            System.out.println();

            UICustomer frontCustomer = new UICustomer(); frontCustomer.showTables();
            System.out.print("Input ID Customer [" + rent.getCustomer().getIdCustomer() + "]: ");
            String strCust = Main.sigmaSkibidi.nextLine();
            if (!strCust.isEmpty()) {
                Customer newCust = new Customer().getByID(Integer.parseInt(strCust));
                if (newCust.getIdCustomer() != 0)
                    rent.setCustomer(newCust);
            }
            System.out.println();

            UIGame frontGame = new UIGame(); frontGame.showTables();
            System.out.print("Input ID Game [" + rent.getGame().getIdGame() + "]: ");
            String strGame = Main.sigmaSkibidi.nextLine();
            if (!strGame.isEmpty()) {
                Game newGame = new Game().getByID(Integer.parseInt(strGame));
                if (newGame.getIdGame() != 0)
                    rent.setGame(newGame);
            }

            System.out.print("Input Trans Date [" + rent.getTransactionDate() + "]: ");
            String strTrans = Main.sigmaSkibidi.nextLine();
            if (!strTrans.isEmpty())
                rent.setTransactionDate(strTrans);

            System.out.print("Input Rent Date [" + rent.getRentDate() + "]: ");
            String strRent = Main.sigmaSkibidi.nextLine();
            if (!strRent.isEmpty())
                rent.setRentDate(strRent);

            System.out.print("Input Return Date [" + rent.getReturnDate() + "]: ");
            String strReturn = Main.sigmaSkibidi.nextLine();
            if (!strReturn.isEmpty())
                rent.setReturnDate(strReturn);

            LocalDate start = LocalDate.parse(rent.getRentDate());
            LocalDate end = LocalDate.parse(rent.getReturnDate());
            long days = ChronoUnit.DAYS.between(start, end);

            if (days < 0) {
                System.out.println("Error: Return Date cannot be before Rent Date! Update Cancelled.");
                return;
            }
            if (days == 0)
                days = 1;

            Game currentGame = new Game().getByID(rent.getGame().getIdGame());
            int pricePerDay = currentGame.getPriceRent();

            int newTotalPrice = pricePerDay * (int) days;
            rent.setTotalPrice(newTotalPrice);

            System.out.println("New Total Price Calculated: " + newTotalPrice);

            rent.save();
            System.out.println("Update successful!");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Error: Invalid date format! Update failed.");
        } catch (Exception e) {
            System.out.println("Error updating: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        System.out.println("-----------------------------------------");
        System.out.println("Delete Renting Transaction");
        System.out.println("-----------------------------------------");
        showTables();

        System.out.print("Enter ID Renting to Delete: ");
        int id = Main.sigmaSkibidi.nextInt();
        Main.sigmaSkibidi.nextLine();

        Renting rent = new Renting();
        rent.setIdRenting(id);
        rent.delete();
        System.out.println("Deleted successfully.");
    }

    @Override
    public void showTables() {
        ArrayList<Renting> list = new Renting().getAll();
        System.out.println(
                "=============================================================================================================");
        System.out.printf("%-4s %-15s %-20s %-12s %-12s %-12s %-12s\n", "ID", "Customer", "Game", "Trans Date",
                "Rent Date", "Return Date", "Total Price");
        System.out.println(
                "=============================================================================================================");

        for (Renting r : list) {
            System.out.printf("%-4d %-15s %-20s %-12s %-12s %-12s %-12d\n",
                    r.getIdRenting(),
                    r.getCustomer().getCustomerName(),
                    r.getGame().getGameTitle(),
                    r.getTransactionDate(),
                    r.getRentDate(),
                    r.getReturnDate(),
                    r.getTotalPrice());
        }
        System.out.println(
                "=============================================================================================================");
    }

    @Override
    public void calculateTotalPricePerCustomer() {
        showTables();
        System.out.println("-----------------------------------------");
        UICustomer frontCustomer = new UICustomer();
        frontCustomer.showTables();
        System.out.print("Input Customer ID: ");
        int custId = Main.sigmaSkibidi.nextInt();
        Customer selectedCustomer = new Customer().getByID(custId);

        if (selectedCustomer.getIdCustomer() == 0) {
            System.out.println("Put a proper ID, buddy.");
            return;
        }
        ArrayList<Renting> listRenting = new Renting().getAll();
        int totalPrice = 0;
        for (Renting r : listRenting) {
            if (r.getCustomer().getIdCustomer() == selectedCustomer.getIdCustomer()) {
                totalPrice += r.getTotalPrice();
            }
        }
        System.out.println("Total Price for " + selectedCustomer.getCustomerName() + ": Rp." + totalPrice);
    }

    // @Override
    // public void calculateTotalPricePerGame() {
    // System.out.println("-----------------------------------------");
    // System.out.println("Total Revenue Per Game");
    // System.out.println("-----------------------------------------");

    // ArrayList<Renting> list = new Renting().getAll();

    // HashMap<String, Integer> gameTotals = new HashMap<>();

    // for (Renting r : list) {
    // String gameTitle = r.getGame().getGameTitle();
    // int price = r.getTotalPrice();

    // if (gameTotals.containsKey(gameTitle)) {
    // gameTotals.put(gameTitle, gameTotals.get(gameTitle) + price);
    // } else {
    // gameTotals.put(gameTitle, price);
    // }
    // }

    // System.out.printf("%-25s %-15s\n", "Game Title", "Total Revenue");
    // System.out.println("-----------------------------------------");
    // for (Map.Entry<String, Integer> entry : gameTotals.entrySet()) {
    // System.out.printf("%-25s %-15d\n", entry.getKey(), entry.getValue());
    // }
    // System.out.println("-----------------------------------------");
    // }

    public void manageRenting() {
        while (true) {
            String mainMenuText = "\n================= Manage Renting ====================\n" +
                    "1. Insert Renting\n" +
                    "2. Update Renting\n" +
                    "3. Delete Renting\n" +
                    "4. Show Tables\n" +
                    "5. Count the total payment for each customer\n" +
                    "0. Back to Main Menu\n" +
                    "===================================================\n" +
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
                    case 5:
                        calculateTotalPricePerCustomer();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid input!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be a number!");
                Main.sigmaSkibidi.nextLine();
            }
        }
    }
}