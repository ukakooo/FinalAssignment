package backend;

import java.util.ArrayList;
import java.sql.*;

public class Purchase {
    private int idPurchase;
    private Customer customer = new Customer();
    private Game game = new Game();
    private int qty;
    private String purchaseDate;
    private int totalPrice;

    public Purchase() {

    }

    public Purchase(Customer customer, Game game, int qty, String purchaseDate, int totalPrice) {
        this.customer = customer;
        this.game = game;
        this.qty = qty;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
    }

    public int getIdPurchase() {
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
                + "t.qty, "
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
                
                purchase.setQty(rs.getInt("qty"));

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
                + "t.qty, "
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
                
                purchase.setQty(rs.getInt("qty"));
                
                purchase.setpurchaseDate(rs.getString("purchaseDate"));
                purchase.setTotalPrice(rs.getInt("totalPrice"));

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
                + "t.qty, "
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
                
                purchase.setQty(rs.getInt("qty"));
                
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
        if (getByID(idPurchase).getIdPurchase() == 0) {
            // Added qty to INSERT statement
            String query = "INSERT INTO purchase (idCustomer, idGame, qty, purchaseDate, totalPrice) VALUES ("
                    + this.customer.getIdCustomer() + ", "
                    + this.game.getIdGame() + ", '"
                    + this.qty + "', '"
                    + this.purchaseDate + "', "
                    + this.totalPrice + ")";
            this.idPurchase = DBHelper.insertQueryGetId(query);
        } else {
            // Added qty to UPDATE statement
            String query = "UPDATE purchase SET "
                    + "idCustomer = '" + this.customer.getIdCustomer() + "', "
                    + "idGame = '" + this.game.getIdGame() + "', "
                    + "qty = '" + this.qty + "', "
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