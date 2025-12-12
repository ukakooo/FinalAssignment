package backend;

import java.util.ArrayList;
import java.sql.*;

public class Genre {
    private int idGenre;
    private String genreName;
    private String genreDesc;

    public Genre() {

    }

    public Genre(String genreName, String genreDesc) {
        this.genreName = genreName;
        this.genreDesc = genreDesc;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreDesc(String genreDesc) {
        this.genreDesc = genreDesc;
    }

    public String getGenreDesc() {
        return genreDesc;
    }

    public Genre getByID(int id) {
        Genre genre = new Genre();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM genre WHERE idGenre = '" + id + "'");
        try {
            while (rs.next()) {
                genre.setIdGenre(rs.getInt("idGenre"));
                genre.setGenreName(rs.getString("genreName"));
                genre.setGenreDesc(rs.getString("genreDesc"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genre;
    }

    public ArrayList<Genre> getAll() {
        ArrayList<Genre> ListGenre = new ArrayList<Genre>();

        ResultSet rs = DBHelper.selectQuery("SELECT * FROM genre");
        try {
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setIdGenre(rs.getInt("idGenre"));
                genre.setGenreName(rs.getString("genreName"));
                genre.setGenreDesc(rs.getString("genreDesc"));
                ListGenre.add(genre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListGenre;
    }

    public ArrayList<Genre> search(String keyword) {
        ArrayList<Genre> ListGenre = new ArrayList<>();

        String sql = "SELECT * FROM genre"
                + " WHERE genreName LIKE '%" + keyword + "%'"
                + " OR genreDesc LIKE '%" + keyword + "%'";
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setIdGenre(rs.getInt("idGenre"));
                genre.setGenreName(rs.getString("genreName"));
                genre.setGenreDesc(rs.getString("genreDesc"));
                ListGenre.add(genre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListGenre;
    }

    public void save() {
        if (getByID(idGenre).getIdGenre() == 0) {
            String sql = "INSERT INTO genre (genreName, genreDesc) VALUES ('" 
            + this.genreName + "', '" 
            + this.genreDesc + "')";
            this.idGenre = DBHelper.insertQueryGetId(sql);
        } else {
            String sql = "UPDATE genre SET genreName = '" 
            + this.genreName + "', genreDesc = '" 
            + this.genreDesc + "' WHERE idGenre = '" 
            + this.idGenre + "'";
            DBHelper.executeQuery(sql);
        }
    }

    public void delete() {
        String sql = "DELETE FROM genre WHERE idGenre = '" + this.idGenre + "'";
        DBHelper.executeQuery(sql);
    }
}
