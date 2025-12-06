package backend;

import java.util.ArrayList;
import java.sql.*;

public class Game {
    private int idGame;
    private String gameTitle;
    private Genre genre = new Genre();
    private int priceBuy;
    private int priceRent;
    private String publisher;
    private String studio;

    public Game() {

    }

    public Game(int idGame, String gameTitle, Genre genre, int priceBuy, int priceRent, String publisher, String studio) {
        this.idGame = idGame;
        this.gameTitle = gameTitle;
        this.genre = genre;
        this.priceBuy = priceBuy;
        this.priceRent = priceRent;
        this.publisher = publisher;
        this.studio = studio;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(int priceBuy) {
        this.priceBuy = priceBuy;
    }

    public int getPriceRent() {
        return priceRent;
    }

    public void setPriceRent(int priceRent) {
        this.priceRent = priceRent;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Game getByID(int id) {
        Game game = new Game();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + " g.idGame, g.gameTitle, g.idGenre, g.priceBuy, g.priceRent, g.publisher, g.studio "
                + " ge.idGenre, ge.genreName"
                + " FROM game g "
                + " LEFT JOIN genre ge ON g.idGenre = ge.idGenre"
                + " WHERE g.idGame = '" + id + "'"
        );
        try {
            while (rs.next()) {
                game.setIdGame(rs.getInt("idGame"));
                game.setGameTitle(rs.getString("gameTitle"));
                game.getGenre().setIdGenre(rs.getInt("idGenre"));
                game.getGenre().setGenreName(rs.getString("genreName"));
                game.setPriceBuy(rs.getInt("priceBuy"));
                game.setPriceRent(rs.getInt("priceRent"));
                game.setPublisher(rs.getString("publisher"));
                game.setStudio(rs.getString("studio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public ArrayList<Game> getAll() {
        ArrayList<Game> ListGame = new ArrayList<Game>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + " g.idGame, g.gameTitle, g.idGenre, g.priceBuy, g.priceRent, g.publisher, g.studio "
                + " ge.idGenre, ge.genreName"
                + " FROM game g "
                + " LEFT JOIN genre ge ON g.idGenre = ge.idGenre"
        );

        try {
            while (rs.next()) {
                Game game = new Game();
                game.setIdGame(rs.getInt("idGame"));
                game.setGameTitle(rs.getString("gameTitle"));
                game.getGenre().setIdGenre(rs.getInt("idGenre"));
                game.getGenre().setGenreName(rs.getString("genreName"));
                game.setPriceBuy(rs.getInt("priceBuy"));
                game.setPriceRent(rs.getInt("priceRent"));
                game.setPublisher(rs.getString("publisher"));
                game.setStudio(rs.getString("studio"));
                ListGame.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListGame;
    }

    public ArrayList<Game> search(String keyword) {
        ArrayList<Game> ListGame = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + " g.idGame, g.gameTitle, g.idGenre, g.priceBuy, g.priceRent, g.publisher, g.studio "
                + " ge.idGenre, ge.genreName"
                + " FROM game g "
                + " LEFT JOIN genre ge ON g.idGenre = ge.idGenre"
                + " WHERE g.gameTitle LIKE '%" + keyword + "%'"
                + " OR ge.genreName LIKE '%" + keyword + "%'"
        );

        try {
            while (rs.next()) {
                Game game = new Game();
                game.setIdGame(rs.getInt("idGame"));
                game.setGameTitle(rs.getString("gameTitle"));
                game.getGenre().setIdGenre(rs.getInt("idGenre"));
                game.getGenre().setGenreName(rs.getString("genreName"));
                game.setPriceBuy(rs.getInt("priceBuy"));
                game.setPriceRent(rs.getInt("priceRent"));
                game.setPublisher(rs.getString("publisher"));
                game.setStudio(rs.getString("studio"));
                ListGame.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListGame;
    }

    public void save() {
        if (getByID(idGame).getIdGame() == 0) {
            String query = "INSERT INTO game (gameTitle, idGenre, priceBuy, priceRent, publisher, studio) VALUES ('"
                    + this.gameTitle + "', '"
                    + this.genre.getIdGenre() + "', '"
                    + this.priceBuy + "', '"
                    + this.priceRent + "', '"
                    + this.publisher + "', '"
                    + this.studio + "')";
            this.idGame = DBHelper.insertQueryGetId(query);
        } else {
            String query = "UPDATE game SET gameTitle = '"
                    + this.gameTitle + "', idGenre = '"
                    + this.genre.getIdGenre() + "', priceBuy = '"
                    + this.priceBuy + "', priceRent = '"
                    + this.priceRent + "', publisher = '"
                    + this.publisher + "', studio = '"
                    + this.studio + "' WHERE idGame = '"
                    + this.idGame + "'";
            DBHelper.executeQuery(query);
        }
    }

    public void delete() {
        String query = "DELETE FROM game WHERE idGame = '" + this.idGame + "'";
        try {
            DBHelper.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
