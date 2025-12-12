package backend;

import java.util.ArrayList;
import java.sql.*;

public class Renting {
    private int idRenting;
    private Customer customer;
    private Game game;
    private String transactionDate;
    private int totalPrice;
    private String rentDate;   
    private String returnDate; 

    public Renting() {
        this.customer = new Customer();
        this.game = new Game();
    }

    public Renting(int idRenting, Customer customer, Game game, String transactionDate, int totalPrice, String rentDate, String returnDate) {
        this.idRenting = idRenting;
        this.customer = customer;
        this.game = game;
        this.transactionDate = transactionDate;
        this.totalPrice = totalPrice;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public Renting(Customer customer, Game game, String transactionDate, int totalPrice, String rentDate, String returnDate) {
        this.customer = customer;
        this.game = game;
        this.transactionDate = transactionDate;
        this.totalPrice = totalPrice; 
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public int getIdRenting() {
        return idRenting;
    }

    public void setIdRenting(int idRenting) {
        this.idRenting = idRenting;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    private String cutDate(String date) {
        if (date != null && date.length() > 10) {
            return date.substring(0, 10);
        }
        return date;
    }

    public Renting getByID(int id) {
        Renting renting = new Renting();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "r.idRenting, "
                + "c.idCustomer, c.customerName, "
                + "g.idGame, g.gameTitle, "
                + "r.transactionDate, r.totalPrice, "
                + "r.rentDate, r.returnDate "
                + "FROM renting r "
                + "LEFT JOIN customer c ON r.idCustomer = c.idCustomer "
                + "LEFT JOIN game g ON r.idGame = g.idGame "
                + "WHERE r.idRenting = '" + id + "'"
        );
        try {
            while (rs.next()) {
                renting.setIdRenting(rs.getInt("idRenting"));
                renting.getCustomer().setIdCustomer(rs.getInt("idCustomer"));
                renting.getCustomer().setCustomerName(rs.getString("customerName"));
                renting.getGame().setIdGame(rs.getInt("idGame"));
                renting.getGame().setGameTitle(rs.getString("gameTitle"));
                renting.setTransactionDate(cutDate(rs.getString("transactionDate")));
                renting.setTotalPrice(rs.getInt("totalPrice"));
                renting.setRentDate(cutDate(rs.getString("rentDate")));
                renting.setReturnDate(cutDate(rs.getString("returnDate")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return renting;
    }

    public ArrayList<Renting> getAll() {
        ArrayList<Renting> ListRenting = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "r.idRenting, "
                + "c.idCustomer, c.customerName, "
                + "g.idGame, g.gameTitle, "
                + "r.transactionDate, r.totalPrice, "
                + "r.rentDate, r.returnDate "
                + "FROM renting r "
                + "LEFT JOIN customer c ON r.idCustomer = c.idCustomer "
                + "LEFT JOIN game g ON r.idGame = g.idGame "
        );
        try {
            while (rs.next()) {
                Renting renting = new Renting();
                renting.setIdRenting(rs.getInt("idRenting"));
                renting.getCustomer().setIdCustomer(rs.getInt("idCustomer"));
                renting.getCustomer().setCustomerName(rs.getString("customerName"));
                renting.getGame().setIdGame(rs.getInt("idGame"));
                renting.getGame().setGameTitle(rs.getString("gameTitle"));
                renting.setTransactionDate(cutDate(rs.getString("transactionDate")));
                renting.setTotalPrice(rs.getInt("totalPrice"));
                renting.setRentDate(cutDate(rs.getString("rentDate")));
                renting.setReturnDate(cutDate(rs.getString("returnDate")));
                
                ListRenting.add(renting);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListRenting;
    }

    public ArrayList<Renting> search(String keyword) {
        ArrayList<Renting> ListRenting = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "r.idRenting, "
                + "c.idCustomer, c.customerName, "
                + "g.idGame, g.gameTitle, "
                + "r.transactionDate, r.totalPrice, "
                + "r.rentDate, r.returnDate "
                + "FROM renting r "
                + "LEFT JOIN customer c ON r.idCustomer = c.idCustomer "
                + "LEFT JOIN game g ON r.idGame = g.idGame "
                + "WHERE c.customerName LIKE '%" + keyword + "%' "
                + "OR g.gameTitle LIKE '%" + keyword + "%' "
        );

        try {
            while (rs.next()) {
                Renting renting = new Renting();
                renting.setIdRenting(rs.getInt("idRenting"));
                renting.getCustomer().setIdCustomer(rs.getInt("idCustomer"));
                renting.getCustomer().setCustomerName(rs.getString("customerName"));
                renting.getGame().setIdGame(rs.getInt("idGame"));
                renting.getGame().setGameTitle(rs.getString("gameTitle"));
                renting.setTransactionDate(cutDate(rs.getString("transactionDate")));
                renting.setTotalPrice(rs.getInt("totalPrice"));
                renting.setRentDate(cutDate(rs.getString("rentDate")));
                renting.setReturnDate(cutDate(rs.getString("returnDate")));
                
                ListRenting.add(renting);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListRenting;
    }

    public void save() {
        if (getByID(idRenting).getIdRenting() == 0) {
            // Insert Query
            String query = "INSERT INTO renting (idCustomer, idGame, transactionDate, totalPrice, rentDate, returnDate) VALUES ("
                    + this.customer.getIdCustomer() + ", "
                    + this.game.getIdGame() + ", '"
                    + this.transactionDate + "', "
                    + this.totalPrice + ", '"
                    + this.rentDate + "', '"
                    + this.returnDate + "')";
            this.idRenting = DBHelper.insertQueryGetId(query);
        } else {
            String query = "UPDATE renting SET "
                    + "idCustomer = '" + this.customer.getIdCustomer() + "', "
                    + "idGame = '" + this.game.getIdGame() + "', "
                    + "transactionDate = '" + this.transactionDate + "', "
                    + "totalPrice = '" + this.totalPrice + "', "
                    + "rentDate = '" + this.rentDate + "', "
                    + "returnDate = '" + this.returnDate + "' "
                    + "WHERE idRenting = '" + this.idRenting + "'";
            DBHelper.executeQuery(query);
        }
    }

    public void delete() {
        String query = "DELETE FROM renting WHERE idRenting = '" + this.idRenting + "'";
        try {
            DBHelper.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}