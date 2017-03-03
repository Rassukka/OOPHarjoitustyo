package main;

import java.sql.*;

public class Database {

    //Singleton patternilla tehty luokka, Hyvä? huono???? who knows...
    //Periaatteessa tarkoittaa vain sitä, että luokasta ei voi koskaan tehdä enempää instanceja kuin vain tämä 1, SQLite sekoaa jos näin tapahtuu
    private static Database instance = new Database();

    private Database(){}

    public static Database getInstance(){
        return instance;
    }

    //Singleton toteutus loppuu --> alle kaikki database metodit, ryhmitellään ne kuitenkin sen mukaan mihin olioon ne vaikuttaa.

    public void lisaaSali(Sali sali) {
        String sql = "INSERT INTO sali(salinNumero, rivit, paikat) " + "VALUES (?,?,?)";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");

            //Käytetään prepared statementteja tälläisiin tilanteisiin nii security on bueno :3 vaikka nää onki vähän raskaita...
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sali.getSalinNumero());
            pstmt.setInt(2, sali.getRivit());
            pstmt.setInt(3, sali.getPaikatRiveilla());

            pstmt.executeUpdate();

            //TODO: Connectionin tuplachekkaus????
            //MUISTA SULKEA CONNECTIONI, ERITTÄIN TÄRKEÄÄ TAI TAPAHTUU KAUHEITA
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO: DET FUNKAR INTE, pitää korjata, en jaksa miettiä enää tänään...
    /*
    public void poistaSali(int salinNumero) {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT salinNumero FROM sali");
            //TODO: ei tällä hetkellä kerro, että löytyikö salia vai ei, poistaa vaan jos sattuu löytämään.
            //TODO: pitäis ehkä käyttää prepared statementtia vaihteeks cba miettii miten se toimis täsä tilanteessa

            while (rs.next()) {
                if (rs.getInt(1) == salinNumero) {
                    stmt.executeUpdate("DELETE FROM sali");
                }
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Sali " + salinNumero + " poistettu onnistuneesti");
    }
    */


    //printtaa databasesta kaikki salit ja niiden infon, myös ID:n!!!
    public void printSalit() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");

            //Kun vaan luetaan tiedostosta voi käyttää ihan vaan resultsettiä, ei tarvii huolehtii prepared statementeista.
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sali");
            while (rs.next())
                System.out.println("database ID: " + rs.getInt(1)
                        + " salin Numero: " + rs.getInt(2)
                        + " rivit: " + rs.getInt(3)
                        + " paikkoja rivillä: " + rs.getInt(4));
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }
}
