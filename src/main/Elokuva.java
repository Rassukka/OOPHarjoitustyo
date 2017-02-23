package main;

/**
 * Created by Durina on 23.2.2017.
 */
public class Elokuva {

    private Tyyppi tyyppi;
    private boolean is2D;
    private String paaHenkilo;
    private int ikaraja;
    private String ensiIlta;

    public Elokuva(Tyyppi tyyppi, boolean is2D, String paaHenkilo, int ikaraja, String ensiIlta) {

        this.tyyppi = tyyppi;
        this.is2D = is2D;
        this.paaHenkilo = paaHenkilo;
        this.ikaraja = ikaraja;
        this.ensiIlta = ensiIlta;

    }
    public Tyyppi getTyyppi() {
        return tyyppi;
    }

    public boolean isIs2D() {
        return is2D;
    }

    public String getPaaHenkilo() {
        return paaHenkilo;
    }

    public int getIkaraja() {
        return ikaraja;
    }

    public String getEnsiIlta() {
        return ensiIlta;
    }

}
