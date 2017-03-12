package main;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    //Singleton patternilla tehty luokka, Hyvä? huono???? who knows...
    //Periaatteessa tarkoittaa vain sitä, että luokasta ei voi koskaan tehdä enempää instanceja kuin vain tämä 1, SQLite sekoaa jos näin tapahtuu
    private static Database instance = new Database();

    private Database() {
    }

    public static Database getInstance() {
        return instance;
    }

    //Singleton toteutus loppuu --> alle kaikki database metodit, ryhmitellään ne kuitenkin sen mukaan mihin olioon ne vaikuttaa.

    public void lisaaSali(Sali sali) {
        String sql = "INSERT INTO sali(salinNumero, rivit, paikatRiveilla) " + "VALUES (?,?,?)";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");

            //Käytetään prepared statementteja tälläisiin tilanteisiin nii security on bueno :3 vaikka nää onki vähän raskaita...
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sali.getSalinNumero());
            pstmt.setInt(2, sali.getRivit());
            pstmt.setInt(3, sali.getPaikatRiveilla());

            pstmt.executeUpdate();

            //MUISTA SULKEA CONNECTIONI, ERITTÄIN TÄRKEÄÄ TAI TAPAHTUU KAUHEITA
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public int[] getSalinNumerot(int salinNumero) {
        String sql = "SELECT * FROM sali WHERE salinNumero=?";
        int[] result = null;

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, salinNumero);
            ResultSet rs = pstmt.executeQuery();

            result = new int[]{rs.getInt(3), rs.getInt(4)};

            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public void poistaSali(int salinNumero) {

        String sql = "DELETE FROM sali WHERE salinNumero=?";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, salinNumero);
            pstmt.executeUpdate();
            System.out.println("Sali " + salinNumero + " poistettu onnistuneesti");

            //TODO: ei tällä hetkellä kerro, että löytyikö salia vai ei, poistaa vaan jos sattuu löytämään.

            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lisaaElokuva(Elokuva elokuva) {
        String sql = "INSERT INTO elokuva(nimi, tyyppi, is3D, paaHenkilo, ikaraja, ensiIlta, salinNumero, vikanaytos, naytosAika, paikat) " + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");

            //Käytetään prepared statementteja tälläisiin tilanteisiin nii security on bueno :3 vaikka nää onki vähän raskaita...
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, elokuva.getNimi());
            pstmt.setString(2, elokuva.getTyyppi().name());
            pstmt.setBoolean(3, elokuva.isIs3D());
            pstmt.setString(4, elokuva.getPaaHenkilo());
            pstmt.setInt(5, elokuva.getIkaraja());
            pstmt.setString(6, elokuva.getEnsiIlta());
            pstmt.setInt(7, elokuva.getSalinNumero());
            pstmt.setObject(8, elokuva.getVikaNaytosPaiva());
            pstmt.setString(9, elokuva.getNaytosAika());
            pstmt.setString(10, elokuva.paikatToDatabaseString());

            pstmt.executeUpdate();

            //MUISTA SULKEA CONNECTIONI, ERITTÄIN TÄRKEÄÄ TAI TAPAHTUU KAUHEITA
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printPaikat(String nimi) {
        String sql = "SELECT * FROM elokuva WHERE nimi=?";
        String dbPaikat = null;

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nimi);
            ResultSet rs = pstmt.executeQuery();
            dbPaikat = rs.getString("paikat");

            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String[] eroteltu = dbPaikat.split(",");

        String print = "";
        String edellinen = "A";
        for (String s : eroteltu) {
            if (!s.substring(0, 1).equals(edellinen)) {
                print = print + "\n";
            }
            edellinen = s.substring(0, 1);
            print = print + s + " | ";
        }
        System.out.print(print);
        System.out.println();
    }

    public void printSalit() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");

            //Kun vaan luetaan tiedostosta voi käyttää ihan vaan resultsettiä, ei tarvii huolehtii prepared statementeista.
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sali");
            while (rs.next()) {
                System.out.println("Salin numero: " + rs.getInt(2));
                for (int i = 1; i <= rs.getInt(4);i++){
                    for (int j = 1;j <= rs.getInt(3);j++){
                        if (j != rs.getInt(3)){ System.out.print("x"); }
                        else{ System.out.println("x " + (rs.getInt(4)-i+1)); break; }
                    }
                }
                String[] aakkoset = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "O", "Å", "Ä", "Ö"};
                for (int i = 1; i <= rs.getInt(3);i++){
                    if(i!=rs.getInt(3)){ System.out.print(aakkoset[i-1]); }
                    else{
                        System.out.println(aakkoset[i-1]);
                        System.out.println("Näyttämö");
                        System.out.println("=======================");
                    }
                }
            }
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

    public void printSali(int sali) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sali");
            while (rs.next()) {
                System.out.println("Sali " + rs.getInt(2));
                for (int i = 1; i <= rs.getInt(4);i++){
                    for (int j = 1;j <= rs.getInt(3);j++){
                        if (j != rs.getInt(3)){ System.out.print("x"); }
                        else{ System.out.println("x"); break; }
                    }
                }
                String[] aakkoset = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "O", "Å", "Ä", "Ö"};
                for (int i = 1; i <= rs.getInt(3);i++){
                    if(i!=rs.getInt(3)){ System.out.print(aakkoset[i-1]); }
                    else{
                        System.out.println(aakkoset[i-1]);
                    }
                }
            }
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void printElokuvat() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM elokuva");
            while (rs.next()) {  //TODO: vielä puuttuu näytös päivät
                System.out.println(" Elokuvan Nimi: " + rs.getString(2) + "\n" +
                        " Päähenkilö: " + rs.getString(5) + "\n" +
                        " Genre: " + rs.getString(3) + "\n" +
                        " Näytösaika: " + rs.getString(10) + "\n" +
                        " Ohjelmistossa: " + rs.getString(7) + " - " + rs.getString(9) + "\n" +
                        " Sali: " + rs.getInt(8) + "\n" +
                        " Onko 3D: " + rs.getBoolean(4) + "\n" +
                        " Ikäraja: " + rs.getInt(6) + "\n" +
                        "========================================"
                );
            }
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }

    }

    public boolean onkoElokuvaa(String nimi) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM elokuva");
            while (rs.next()) {
                if ((rs.getString(2).compareTo(nimi)) == 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public String[] elokuvanTiedot(String nimi){
        String[] tiedot = new String[11];
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM elokuva");
            while (rs.next()) {
                if ((rs.getString(2).compareTo(nimi)) == 0) {
                    for(int i = 2; i<10; i++){
                        System.out.println(rs.getString(i));
                        tiedot[i-2]= (rs.getString(i));
                    }
                }
            }
            return tiedot;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return tiedot;
        }
    }
}
