package main;

import java.util.ArrayList;

public class Sali {

    private int salinNumero;
    private int rivit;
    private int paikatRiveilla;

    public Sali(int salinNumero, int rivit, int paikatRiveilla) {
        this.salinNumero = salinNumero;
        this.rivit = rivit;
        this.paikatRiveilla = paikatRiveilla;
    }

    public int getSalinNumero() {
        return salinNumero;
    }

    public void setSalinNumero(int salinNumero) {
        this.salinNumero = salinNumero;
    }

    public int getRivit() {
        return rivit;
    }

    public void setRivit(int rivit) {
        this.rivit = rivit;
    }

    public int getPaikatRiveilla() {
        return paikatRiveilla;
    }

    public void setPaikatRiveilla(int paikatRiveilla) {
        this.paikatRiveilla = paikatRiveilla;
    }

}
