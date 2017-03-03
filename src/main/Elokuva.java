package main;

public class Elokuva {

    //TODO: Näytösajat? poistuu automaattisesti databasesta jos päivämäärä on mennyt jo?
    //Esim. kun ohjelma käynnistyy, chekkaa päivämäärän ja ajan, jos databasessa on jokin jo mennyt leffa, se poistaa sen sieltä.

    //Jokainen leffa saa salin numeron myös parametrina, jotta voidaan ryhmitellä ja hakea leffat salien mukaan.

    //TODO: paikat ja paikkojen varaustilanne pitäisi varmaan lisätä tänne jotenkin, en oo vielä ihan varma miten se tullaan toteuttaa tho...

    private Tyyppi tyyppi;
    private boolean is3D;
    private String paaHenkilo;
    private int ikaraja;
    private String ensiIlta;
    private int salinNumero;

    public Elokuva(Tyyppi tyyppi, boolean is3D, String paaHenkilo, int ikaraja, String ensiIlta, int salinNumero) {

        this.tyyppi = tyyppi;
        this.is3D = is3D;
        this.paaHenkilo = paaHenkilo;
        this.ikaraja = ikaraja;
        this.ensiIlta = ensiIlta;
        this.salinNumero = salinNumero;

    }

    public Tyyppi getTyyppi() {
        return tyyppi;
    }

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

}
