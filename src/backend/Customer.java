package backend;

import java.util.ArrayList;
import java.sql.*;

//

public class Customer {

    private int idCustomer;
    private String customerName;
    private String customerTelp;

    public Customer() {

    }

    public Customer(int idCustomer, String customerName, String customerTelp) {
        this.idCustomer = idCustomer;
        this.customerName = customerName;
        this.customerTelp = customerTelp;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTelp() {
        return customerTelp;
    }

    public void setCustomerTelp(String customerTelp) {
        this.customerTelp = customerTelp;
    }

    public Customer getByID(int id) {
        Customer cust = new Customer();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM customer WHERE idCustomer = '" + id + "'");

        try {
            while (rs.next()) {
                cust = new Customer(); 
                cust.setIdCustomer(rs.getInt("idCustomer"));
                cust.setCustomerName(rs.getString("customerName"));
                cust.setCustomerTelp(rs.getString("customerTelp"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cust;
    }

    public ArrayList<Customer> getAll() {
        ArrayList<Customer> listCustomer = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM customer");

        try {
            while (rs.next()) {
                Customer cust = new Customer();
                cust.setIdCustomer(rs.getInt("idCustomer"));
                cust.setCustomerName(rs.getString("customerName"));
                cust.setCustomerTelp(rs.getString("customerTelp"));

                listCustomer.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCustomer;
    }

    public ArrayList<Customer> search(String keyword) {
        ArrayList<Customer> listCustomer = new ArrayList<>();

        String sql = "SELECT * FROM customer WHERE "
                + "customerName LIKE '%" + keyword + "%' "
                + "OR customerTelp LIKE '%" + keyword + "%'";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Customer cust = new Customer();
                cust.setIdCustomer(rs.getInt("idCustomer"));
                cust.setCustomerName(rs.getString("customerName"));
                cust.setCustomerTelp(rs.getString("customerTelp"));

                listCustomer.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCustomer;
    }

    public void save() {
        if (getByID(idCustomer).getIdCustomer() == 0) {
            String query = "INSERT INTO customer (customerName, customerTelp) VALUES ('"
                    + this.customerName + "', '"
                    + this.customerTelp + "')";
            this.idCustomer = DBHelper.insertQueryGetId(query);
        } else {
            String query = "UPDATE customer SET "
                    + "customerName = '" + this.customerName + "', "
                    + "customerTelp = '" + this.customerTelp + "' "
                    + "WHERE idCustomer = '" + this.idCustomer + "'";
            DBHelper.executeQuery(query);
        }
    }

    public void delete() {
        String query = "DELETE FROM customer WHERE idCustomer = '" + this.idCustomer + "'";
        try {
            DBHelper.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
