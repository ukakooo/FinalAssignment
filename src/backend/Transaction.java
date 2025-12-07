package backend;

import java.util.ArrayList;
import java.sql.*;

public class Transaction {
    private int idTransaction;
    private Customer customer = new Customer();
    private Game game = new Game();
    private String transactionDate;
    private int totalPrice;

    public Transaction() {

    }

    public Transaction(int idTransaction, Customer customer, Game game, String transactionDate, int totalPrice) {
        this.idTransaction = idTransaction;
        this.customer = customer;
        this.game = game;
        this.transactionDate = transactionDate;
        this.totalPrice = totalPrice;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
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

    public Transaction getByID(int id) {
        Transaction transaction = new Transaction();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "t.idTransaction, "
                + "c.idCustomer, c.customerName,"
                + "g.idGame, g.gameTitle, "
                + "t.transactionDate, t.totalPrice "
                + "FROM transaction t "
                + "LEFT JOIN customer c ON t.idCustomer = c.idCustomer "
                + "LEFT JOIN game g ON t.idGame = g.idGame "
                + "WHERE t.idTransaction = '" + id + "'"
        );
        try {
            while (rs.next()) {
                transaction.setIdTransaction(rs.getInt("idTransaction"));
                transaction.getCustomer().setIdCustomer(rs.getInt("idCustomer"));
                transaction.getGame().setIdGame(rs.getInt("idGame"));
                transaction.setTransactionDate(rs.getString("transactionDate"));
                transaction.setTotalPrice(rs.getInt("totalPrice"));

                transaction.getCustomer().setCustomerName(rs.getString("customerName"));
                transaction.getGame().setGameTitle(rs.getString("gameTitle"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public ArrayList<Transaction> getAll() {
        ArrayList<Transaction> ListTransaction = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "t.idTransaction, "
                + "c.idCustomer, c.customerName,"
                + "g.idGame, g.gameTitle, "
                + "t.transactionDate, t.totalPrice "
                + "FROM transaction t "
                + "LEFT JOIN customer c ON t.idCustomer = c.idCustomer "
                + "LEFT JOIN game g ON t.idGame = g.idGame "
        );
        try {
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setIdTransaction(rs.getInt("idTransaction"));
                transaction.getCustomer().setIdCustomer(rs.getInt("idCustomer"));
                transaction.getGame().setIdGame(rs.getInt("idGame"));
                transaction.setTransactionDate(rs.getString("transactionDate"));
                transaction.setTotalPrice(rs.getInt("totalPrice"));

                // Also populate names for the list
                transaction.getCustomer().setCustomerName(rs.getString("customerName"));
                transaction.getGame().setGameTitle(rs.getString("gameTitle"));
                
                ListTransaction.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListTransaction;
    }

    public ArrayList<Transaction> search(String keyword) {
        ArrayList<Transaction> ListTransaction = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "t.idTransaction, "
                + "c.idCustomer, c.customerName,"
                + "g.idGame, g.gameTitle, "
                + "t.transactionDate, t.totalPrice "
                + "FROM transaction t "
                + "LEFT JOIN customer c ON t.idCustomer = c.idCustomer "
                + "LEFT JOIN game g ON t.idGame = g.idGame "
                + "WHERE c.customerName LIKE '%" + keyword + "%' "
                + "OR g.gameTitle LIKE '%" + keyword + "%' "
        );

        try {
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setIdTransaction(rs.getInt("idTransaction"));
                transaction.getCustomer().setIdCustomer(rs.getInt("idCustomer"));
                transaction.getCustomer().setCustomerName(rs.getString("customerName"));
                transaction.getGame().setIdGame(rs.getInt("idGame"));
                transaction.getGame().setGameTitle(rs.getString("gameTitle"));
                transaction.setTransactionDate(rs.getString("transactionDate"));
                transaction.setTotalPrice(rs.getInt("totalPrice"));
                ListTransaction.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListTransaction;
    }

    public void save() {
        if (getByID(idTransaction).getIdTransaction() == 0) {
            String query = "INSERT INTO transaction (idCustomer, idGame, transactionDate, totalPrice) VALUES ("
                    + this.customer.getIdCustomer() + ", "
                    + this.game.getIdGame() + ", '"
                    + this.transactionDate + "', "
                    + this.totalPrice + ")";
            this.idTransaction = DBHelper.insertQueryGetId(query);
        } else {
            String query = "UPDATE transaction SET "
                    + "idCustomer = '" + this.customer.getIdCustomer() + "', "
                    + "idGame = '" + this.game.getIdGame() + "', "
                    + "transactionDate = '" + this.transactionDate + "', "
                    + "totalPrice = '" + this.totalPrice + "' "
                    + "WHERE idTransaction = '" + this.idTransaction + "'";
            DBHelper.executeQuery(query);
        }
    }

    public void delete() {
        String query = "DELETE FROM transaction WHERE idTransaction = '" + this.idTransaction + "'";
        try {
            DBHelper.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}