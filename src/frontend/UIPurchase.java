package frontend;

import java.util.ArrayList;
import backend.*;

public class UIPurchase extends Transaction{
    public void managePurchase() {
        String mainMenuText =
                        "================= PURCHASE MENU =====================\n" +
                        "1. Add Purchase\n" +
                        "2. Update Purchase\n" +
                        "3. Delete Purchase\n" +
                        "0. Exit\n" +
                        "=====================================================\n" +
                        "Choose Menu: ";
        
        try {
            System.out.print(mainMenuText);
            int choice = Main.sigmaSkibidi.nextInt();
            Main.sigmaSkibidi.nextLine();
            Purchase purchase;
            ArrayList<Purchase> listPurchase = new Purchase().getAll();

            switch (choice) {
                case 1:
                    UICustomer frontCustomer = new UICustomer(); frontCustomer.showTables();
                    System.out.print("Input Customer ID: ");
                    int customerID = Main.sigmaSkibidi.nextInt();
                    System.out.print("Input Game ID: ");
                    int gameID = Main.sigmaSkibidi.nextInt();
                    System.out.print("Input Quantity: ");
                    int quantity = Main.sigmaSkibidi.nextInt();
                    Main.sigmaSkibidi.nextLine();
                    String purchaseDate = java.time.LocalDate.now().toString();
                    int totalPrice = gameID * quantity;
                    purchase = new Purchase(customerID, gameID, quantity, purchaseDate, totalPrice);
                    purchase.save();
                    break;
                case 2:
                    
                    break;
                case 3:
                    
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
}
