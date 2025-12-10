package frontend;

import java.util.ArrayList;
import java.util.InputMismatchException;

import backend.*;

public class UICustomer {
    public void manageCustomer() {
        String mainMenuText = "================= Manage Customer ====================\n" +
                "1. Insert new Customer\n" +
                "2. Update Customer\n" +
                "3. Delete Customer\n" +
                "4. Show Tables\n" +
                "0. Back to Main Menu\n" +
                "======================================================\n" +
                "Choose Menu: ";
        try {
            System.out.print(mainMenuText);
            int choice = Main.sigmaSkibidi.nextInt();
            Main.sigmaSkibidi.nextLine();
            Customer customer;
            ArrayList<Customer> listCustomer = new Customer().getAll();

            switch (choice) {
                case 1:
                    System.out.print("Input Customer Name: ");
                    String customerName = Main.sigmaSkibidi.nextLine();
                    System.out.print("Input Customer Telp: ");
                    String customerTelp = Main.sigmaSkibidi.nextLine();
                    customer = new Customer(customerName, customerTelp);
                    customer.save();
                    listCustomer = customer.getAll();
                    System.out.println("New Customer added successfully!");
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Telp");
                    System.out.println("==================================================================");
                    if (!listCustomer.isEmpty()) {
                        Customer lastCustomer = listCustomer.get(listCustomer.size() - 1);
                        System.out.printf("%-5s %-25s %-20s\n", lastCustomer.getIdCustomer(),
                                lastCustomer.getCustomerName(),
                                lastCustomer.getCustomerTelp());
                    }
                    System.out.println("==================================================================");
                    break;

                case 2:
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Telp");
                    System.out.println("==================================================================");
                    for (Customer c : listCustomer) {
                        System.out.printf("%-5d %-25s %-20s\n", c.getIdCustomer(), c.getCustomerName(),
                                c.getCustomerTelp());
                    }
                    System.out.println("==================================================================");
                    System.out.println("");
                    System.out.print("Input ID Customer: ");
                    int idCustomer = Main.sigmaSkibidi.nextInt();
                    Customer oldCustData = new Customer().getByID(idCustomer);

                    Customer checkCustomer = new Customer().getByID(idCustomer);

                    if (checkCustomer.getIdCustomer() == 0) {
                        System.out.println("Put a proper ID, buddy.");
                        break;
                    }

                    Main.sigmaSkibidi.nextLine();
                    System.out.print("Input Customer Name (Press Enter to keep unchanged): ");
                    String customerName2 = Main.sigmaSkibidi.nextLine();

                    if (customerName2.isEmpty()) {
                        customerName2 = oldCustData.getCustomerName();
                    }

                    System.out.print("Input Customer Telp (Press Enter to keep unchanged): ");
                    String customerTelp2 = Main.sigmaSkibidi.nextLine();

                    if (customerTelp2.isEmpty()) {
                        customerTelp2 = oldCustData.getCustomerTelp();
                    }

                    customer = new Customer(customerName2, customerTelp2);
                    customer.setIdCustomer(idCustomer);
                    customer.save();
                    listCustomer = customer.getAll();
                    System.out.println("Customer updated successfully!");
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Telp");
                    System.out.println("==================================================================");
                    if (!listCustomer.isEmpty()) {
                        for (Customer c : listCustomer) {
                            System.out.printf("%-5s %-25s %-20s\n", c.getIdCustomer(), c.getCustomerName(),
                                    c.getCustomerTelp());
                        }
                    }
                    System.out.println("==================================================================");
                    break;

                case 3:
                    System.out.println("==================================================================");
                    System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Telp");
                    System.out.println("==================================================================");
                    for (Customer c : listCustomer) {
                        System.out.printf("%-5s %-25s %-20s\n", c.getIdCustomer(), c.getCustomerName(),
                                c.getCustomerTelp());
                    }
                    System.out.println("==================================================================");
                    System.out.print("Enter the Customer ID: ");
                    int customerIdDel = Main.sigmaSkibidi.nextInt();

                    Customer targetCustomer = new Customer().getByID(customerIdDel);

                    if (targetCustomer.getIdCustomer() == 0) {
                        System.out.println("Put a proper ID, buddy.");
                        break;
                    }

                    Main.sigmaSkibidi.nextLine();
                    customer = new Customer();
                    customer.setIdCustomer(customerIdDel);
                    customer.delete();
                    System.out.println("Customer deleted successfully!");
                    break;

                case 4:
                    showTables();
                    break;
                case 0:
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public void showTables() {
        ArrayList<Customer> listCustomer = new Customer().getAll();
        System.out.println("========================= Customer's Data =========================");
        System.out.printf("%-5s %-25s %-20s\n", "ID", "Name", "Telep");
        System.out.println("===================================================================");
        for (Customer c : listCustomer) {
            System.out.printf("%-5d %-25s %-20s\n", c.getIdCustomer(), c.getCustomerName(), c.getCustomerTelp());
        }
        System.out.println("===================================================================");
    }
}