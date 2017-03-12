package main;


import java.time.LocalDate; // päivämäärät tällai
import java.util.ArrayList;

public class Elokuva {

    //TODO: Näytösajat? poistuu automaattisesti databasesta jos päivämäärä on mennyt jo?
    //Esim. kun ohjelma käynnistyy, chekkaa päivämäärän ja ajan, jos databasessa on jokin jo mennyt leffa, se poistaa sen sieltä.

    //Jokainen leffa saa salin numeron myös parametrina, jotta voidaan ryhmitellä ja hakea leffat salien mukaan.

    //TODO: paikat ja paikkojen varaustilanne pitäisi varmaan lisätä tänne jotenkin, en oo vielä ihan varma miten se tullaan toteuttaa tho...

    private String nimi;
    private Tyyppi tyyppi;
    private boolean is3D;
    private String paaHenkilo;
    private int ikaraja;
    private String ensiIlta;
    private int salinNumero;
    private LocalDate vikaNaytosPaiva; //vika päivä ku leffa pyörii?
    private String naytosAika; // esim "18:00"
    private ArrayList<Paikat> paikat;

    public Elokuva(String nimi, Tyyppi tyyppi, boolean is3D, String paaHenkilo, int ikaraja, String ensiIlta, int salinNumero, LocalDate vikaNaytosPaiva, String naytosAika) {

        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.is3D = is3D;
        this.paaHenkilo = paaHenkilo;
        this.ikaraja = ikaraja;
        this.ensiIlta = ensiIlta;
        this.salinNumero = salinNumero;
        this.vikaNaytosPaiva = vikaNaytosPaiva;
        this.naytosAika = naytosAika;
        this.paikat = teePaikat();

    }

    public String getNimi() { return nimi; }

    public void setNimi(String nimi) { this.nimi = nimi; }

    public Tyyppi getTyyppi() { return tyyppi; }

    public void setTyyppi(Tyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public String getPaaHenkilo() {
        return paaHenkilo;
    }

    public void setPaaHenkilo(String paaHenkilo) {
        this.paaHenkilo = paaHenkilo;
    }

    public int getIkaraja() {
        return ikaraja;
    }

    public void setIkaraja(int ikaraja) {
        this.ikaraja = ikaraja;
    }

    public String getEnsiIlta() {
        return ensiIlta;
    }

    public void setEnsiIlta(String ensiIlta) {
        this.ensiIlta = ensiIlta;
    }

    public int getSalinNumero() {
        return salinNumero;
    }

    public void setSalinNumero(int salinNumero) {
        this.salinNumero = salinNumero;
    }

    public LocalDate getVikaNaytosPaiva() { return vikaNaytosPaiva; }

    public void setVikaNaytosPaiva(LocalDate vikaNaytosPaiva) { this.vikaNaytosPaiva = vikaNaytosPaiva; }

    public String getNaytosAika() { return naytosAika; }

    public void setNaytosAika(String naytosAika) { this.naytosAika = naytosAika; }

    public ArrayList<Paikat> getPaikat() { return paikat; }

    public String paikatToDatabaseString() {
        String string = "";

        //Tallennettu nyt databaseen näin, saa muuttaa jos tulee parempi idea...
        for (Paikat p : paikat) {
            string = string + p.getPaikka() + ":" + p.stringVapaa() + ",";
        }

        return string;
    }

    //TODO: Print funktio joka printtaa vapaat paikat nätisti ulos

    private ArrayList<Paikat> teePaikat() {

        Database db = Database.getInstance();

        int[] numerot = db.getSalinNumerot(salinNumero);

        paikat = new ArrayList<>();

        String[] aakkoset = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "O", "Å", "Ä", "Ö"};

        for (int i = 0; i < numerot[0]; i++) {
            for (int j = 1; j <= numerot[1]; j++) {
                paikat.add(new Paikat(aakkoset[i] + j, true));
            }
        }
        return paikat;
    }

}
