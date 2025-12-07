package backend;

import java.util.ArrayList;
import java.sql.*;

public class Purchase {
    private int idPurchase;
    private Customer customer = new Customer();
    private Game game = new Game();
    private String purchaseDate;
    private int totalPrice;

    public Purchase() {

    }

    public Purchase(int idPurchase, Customer customer, Game game, String purchaseDate, int totalPrice) {
        this.idPurchase = idPurchase;
        this.customer = customer;
        this.game = game;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
    }

    public int getidPurchase() {
        return idPurchase;
    }

    public void setidPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
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

    public String getpurchaseDate() {
        return purchaseDate;
    }

    public void setpurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Purchase getByID(int id) {
        Purchase purchase = new Purchase();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "t.idPurchase, "
                + "c.idCustomer, c.customerName,"
                + "g.idGame, g.gameTitle, "
                + "t.purchaseDate, t.totalPrice "
                + "FROM purchase t "
                + "LEFT JOIN customer c ON t.idCustomer = c.idCustomer "
                + "LEFT JOIN game g ON t.idGame = g.idGame "
                + "WHERE t.idPurchase = '" + id + "'"
        );
        try {
            while (rs.next()) {
                purchase.setidPurchase(rs.getInt("idPurchase"));
                purchase.getCustomer().setIdCustomer(rs.getInt("idCustomer"));
                purchase.getGame().setIdGame(rs.getInt("idGame"));
                purchase.setpurchaseDate(rs.getString("purchaseDate"));
                purchase.setTotalPrice(rs.getInt("totalPrice"));

                purchase.getCustomer().setCustomerName(rs.getString("customerName"));
                purchase.getGame().setGameTitle(rs.getString("gameTitle"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return purchase;
    }

    public ArrayList<Purchase> getAll() {
        ArrayList<Purchase> Listpurchase = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "t.idPurchase, "
                + "c.idCustomer, c.customerName,"
                + "g.idGame, g.gameTitle, "
                + "t.purchaseDate, t.totalPrice "
                + "FROM purchase t "
                + "LEFT JOIN customer c ON t.idCustomer = c.idCustomer "
                + "LEFT JOIN game g ON t.idGame = g.idGame "
        );
        try {
            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setidPurchase(rs.getInt("idPurchase"));
                purchase.getCustomer().setIdCustomer(rs.getInt("idCustomer"));
                purchase.getGame().setIdGame(rs.getInt("idGame"));
                purchase.setpurchaseDate(rs.getString("purchaseDate"));
                purchase.setTotalPrice(rs.getInt("totalPrice"));

                // Also populate names for the list
                purchase.getCustomer().setCustomerName(rs.getString("customerName"));
                purchase.getGame().setGameTitle(rs.getString("gameTitle"));
                
                Listpurchase.add(purchase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Listpurchase;
    }

    public ArrayList<Purchase> search(String keyword) {
        ArrayList<Purchase> Listpurchase = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "t.idPurchase, "
                + "c.idCustomer, c.customerName,"
                + "g.idGame, g.gameTitle, "
                + "t.purchaseDate, t.totalPrice "
                + "FROM purchase t "
                + "LEFT JOIN customer c ON t.idCustomer = c.idCustomer "
                + "LEFT JOIN game g ON t.idGame = g.idGame "
                + "WHERE c.customerName LIKE '%" + keyword + "%' "
                + "OR g.gameTitle LIKE '%" + keyword + "%' "
        );

        try {
            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setidPurchase(rs.getInt("idPurchase"));
                purchase.getCustomer().setIdCustomer(rs.getInt("idCustomer"));
                purchase.getCustomer().setCustomerName(rs.getString("customerName"));
                purchase.getGame().setIdGame(rs.getInt("idGame"));
                purchase.getGame().setGameTitle(rs.getString("gameTitle"));
                purchase.setpurchaseDate(rs.getString("purchaseDate"));
                purchase.setTotalPrice(rs.getInt("totalPrice"));
                Listpurchase.add(purchase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Listpurchase;
    }

    public void save() {
        if (getByID(idPurchase).getidPurchase() == 0) {
            String query = "INSERT INTO purchase (idCustomer, idGame, purchaseDate, totalPrice) VALUES ("
                    + this.customer.getIdCustomer() + ", "
                    + this.game.getIdGame() + ", '"
                    + this.purchaseDate + "', "
                    + this.totalPrice + ")";
            this.idPurchase = DBHelper.insertQueryGetId(query);
        } else {
            String query = "UPDATE purchase SET "
                    + "idCustomer = '" + this.customer.getIdCustomer() + "', "
                    + "idGame = '" + this.game.getIdGame() + "', "
                    + "purchaseDate = '" + this.purchaseDate + "', "
                    + "totalPrice = '" + this.totalPrice + "' "
                    + "WHERE idPurchase = '" + this.idPurchase + "'";
            DBHelper.executeQuery(query);
        }
    }

    public void delete() {
        String query = "DELETE FROM purchase WHERE idPurchase = '" + this.idPurchase + "'";
        try {
            DBHelper.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}