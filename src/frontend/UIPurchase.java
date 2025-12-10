package frontend;

import java.util.ArrayList;
import backend.*;

public class UIPurchase extends Transaction {
    public void managePurchase() {
        String mainMenuText = "================= PURCHASE MENU =====================\n" +
                "1. Add Purchase\n" +
                "2. Update Purchase\n" +
                "3. Delete Purchase\n" +
                "4. Show Tables\n" +
                "5. Count the total payment for each customer\n" +
                "0. Exit\n" +
                "=====================================================\n" +
                "Choose Menu: ";

        try {
            System.out.print(mainMenuText);
            int choice = Main.sigmaSkibidi.nextInt();
            Main.sigmaSkibidi.nextLine();

            ArrayList<Purchase> listPurchase = new Purchase().getAll();

            switch (choice) {
                case 1:
                    boolean repeat = true;
                    UICustomer frontCustomer = new UICustomer();
                    System.out.println();
                    frontCustomer.showTables();
                    System.out.print("Input Customer ID: ");
                    int customerID = Main.sigmaSkibidi.nextInt();
                    

                    Customer cust = new Customer().getByID(customerID);

                    if (cust.getIdCustomer() == 0) {
                        System.out.println("Put a proper ID, buddy.");
                        break;
                    }

                    do {
                        UIGame frontGame = new UIGame();
                        System.out.println();
                        frontGame.showTables();
                        System.out.print("Input Game ID: ");
                        int gameID = Main.sigmaSkibidi.nextInt();

                        Game game = new Game().getByID(gameID);

                        if (game.getIdGame() == 0) {
                            System.out.println("Put a proper ID, buddy.");
                            continue;
                        }

                        System.out.print("Input Quantity: ");
                        int quantity = Main.sigmaSkibidi.nextInt();

                        if (quantity <= 0) {
                            System.out.println("Put a proper quantity, buddy.");
                            continue;
                        }

                        Main.sigmaSkibidi.nextLine();
                        String purchaseDate = java.time.LocalDate.now().toString();
                        int totalPrice = game.getPriceBuy() * quantity;

                        Purchase purchase = new Purchase(cust, game, quantity, purchaseDate, totalPrice);

                        purchase.save();
                        listPurchase = purchase.getAll();
                        System.out.println("Purchase added successfully.");
                        System.out.println("==================================================================");
                        System.out.printf("%-5s %-25s %-30s %-5s %-20s\n", "ID", "Customer Name", "Game Title", "Qty", "Total Price");
                        System.out.println("==================================================================");
                        if (!listPurchase.isEmpty()) {
                            Purchase lastPurchase = listPurchase.get(listPurchase.size() - 1);
                            System.out.printf("%-5s %-25s %-30s %-5s %-20s\n", lastPurchase.getIdPurchase(),
                                    lastPurchase.getCustomer().getCustomerName(),
                                    lastPurchase.getGame().getGameTitle(), lastPurchase.getQty(),
                                    "Rp." + lastPurchase.getTotalPrice());
                        }
                        System.out.println("==================================================================");

                        System.out.print("Do you want to add another game? (y/n): ");
                        String answer = Main.sigmaSkibidi.nextLine();
                        if (answer.equalsIgnoreCase("y")) {
                            repeat = true;
                        } else {
                            repeat = false;
                        }
                    } while (repeat);
                    break;

                case 2:
                    System.out.println();
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-30s %-5s %-20s\n", "ID", "Customer Name", "Game Title", "Qty", "Total Price");
                    System.out.println("==================================================================");
                    if (!listPurchase.isEmpty()) {
                        for (Purchase p : listPurchase) {
                            System.out.printf("%-5s %-25s %-30s %-5s %-20s\n", p.getIdPurchase(),
                                    p.getCustomer().getCustomerName(),
                                    p.getGame().getGameTitle(), p.getQty(),
                                    "Rp." + p.getTotalPrice());
                        }
                        System.out.println("==================================================================");
                    }

                    System.out.print("Input Purchase ID: ");
                    int purchaseID = Main.sigmaSkibidi.nextInt();
                    Purchase oldPurchaseData = new Purchase().getByID(purchaseID);

                    Purchase checkPurchase = new Purchase().getByID(purchaseID);

                    if (checkPurchase.getIdPurchase() == 0) {
                        System.out.println("Put a proper ID, buddy.");
                        break;
                    }

                    UICustomer frontCustomer2 = new UICustomer();
                    System.out.println();
                    frontCustomer2.showTables();

                    // Customer Input
                    System.out.print("Input Customer ID (Press Enter to keep unchanged): ");
                    String custUpd = Main.sigmaSkibidi.nextLine();
                    Main.sigmaSkibidi.nextLine();

                    Customer selectedCustUpd;

                    if (custUpd.isEmpty()) {
                        selectedCustUpd = oldPurchaseData.getCustomer();
                    } else {
                        selectedCustUpd = new Customer().getByID(Integer.parseInt(custUpd));
                        if (selectedCustUpd.getIdCustomer() == 0) {
                            System.out.println("Put a proper ID, buddy.");
                            break;
                        }
                    }

                    // Game Input
                    UIGame frontGame2 = new UIGame();
                    frontGame2.showTables();
                    System.out.print("Input Game ID (Press Enter to keep unchanged): ");
                    String gameUpd = Main.sigmaSkibidi.nextLine();

                    Game selectedGameUpd;

                    if (gameUpd.isEmpty()) {
                        selectedGameUpd = oldPurchaseData.getGame();
                    } else {
                        selectedGameUpd = new Game().getByID(Integer.parseInt(gameUpd));
                        if (selectedGameUpd.getIdGame() == 0) {
                            System.out.println("Put a proper ID, buddy.");
                            break;
                        }
                    }

                    System.out.print("Input Quantity: ");
                    int quantity = Main.sigmaSkibidi.nextInt();

                    if (quantity <= 0) {
                        System.out.println("Quantity can't be negative.");
                        break;
                    }

                    int totalPrice = selectedGameUpd.getPriceBuy() * quantity;

                    Purchase purchase = new Purchase(selectedCustUpd, selectedGameUpd, quantity, oldPurchaseData.getpurchaseDate(), totalPrice);
                    purchase.setidPurchase(purchaseID);
                    purchase.save();

                    listPurchase = purchase.getAll();
                    System.out.println("Purchase updated successfully.");
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-30s %-5s %-20s\n", "ID", "Customer Name", "Game Title", "Qty", "Total Price");
                    System.out.println("==================================================================");
                    if (!listPurchase.isEmpty()) {
                        Purchase lastPurchase = listPurchase.get(listPurchase.size() - 1);
                        System.out.printf("%-5s %-25s %-25s %-5s %-20s\n", lastPurchase.getIdPurchase(),
                                lastPurchase.getCustomer().getCustomerName(),
                                lastPurchase.getGame().getGameTitle(), lastPurchase.getQty(),
                                "Rp." + lastPurchase.getTotalPrice());
                    }
                    System.out.println("==================================================================");

                    break;
                case 3:
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-30s %-5s %-20s\n", "ID", "Customer Name", "Game Title", "Qty", "Total Price");
                    System.out.println("==================================================================");
                    if (!listPurchase.isEmpty()) {
                        for (Purchase p : listPurchase) {
                            System.out.printf("%-5s %-25s %-30s %-5s %-20s\n", p.getIdPurchase(),
                                    p.getCustomer().getCustomerName(),
                                    p.getGame().getGameTitle(), p.getQty(),
                                    "Rp." + p.getTotalPrice());
                        }
                        System.out.println("==================================================================");
                    }
                    System.out.print("Input Purchase ID: ");
                    int purchaseIdDel = Main.sigmaSkibidi.nextInt();
                    purchase = new Purchase();
                    purchase.setidPurchase(purchaseIdDel);
                    purchase.delete();
                    System.out.println("Purchase deleted successfully.");
                    break;

                case 4:
                    showTables();
                    break;

                case 5:
                    calculateTotalPricePerCustomer();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showTables() {
        ArrayList<Purchase> listPurchase = new Purchase().getAll();
        System.out.println("==================================================================");
        System.out.printf("%-5s %-15s %-25s %-30s %-5s %-20s\n", "ID", "Customer ID","Customer Name", "Game Title", "Qty", "Total Price");
        System.out.println("==================================================================");
        for (Purchase p : listPurchase) {
            System.out.printf("%-5s %-15s %-25s %-30s %-5s %-20s\n", p.getIdPurchase(), p.getCustomer().getIdCustomer(),
                    p.getCustomer().getCustomerName(),
                    p.getGame().getGameTitle(), p.getQty(),
                    "Rp." + p.getTotalPrice());
        }
        System.out.println("==================================================================");
    }

    @Override
    public void calculateTotalPricePerCustomer() {
        showTables();
        System.out.print("Input Customer ID: ");
        int custId = Main.sigmaSkibidi.nextInt();
        Customer selectedCustomer = new Customer().getByID(custId);
        
        if (selectedCustomer.getIdCustomer() == 0) {
            System.out.println("Put a proper ID, buddy.");
            return;
        }
        ArrayList<Purchase> listPurchase = new Purchase().getAll();
        int totalPrice = 0;
        for (Purchase p : listPurchase) {
            if (p.getCustomer().getIdCustomer() == selectedCustomer.getIdCustomer()) {
                totalPrice += p.getTotalPrice();
            }
        }
        System.out.println("Total Price for " + selectedCustomer.getCustomerName() + ": Rp." + totalPrice);
    }
}
