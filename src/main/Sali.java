package main;

/**
 * Created by Durina on 23.2.2017.
 */
public class Sali extends Elokuvateatteri{

    private int rivit;
    private int paikatRiveilla;

    public Sali(int rivit, int paikatRiveilla) {
        this.rivit = rivit;
        this.paikatRiveilla = paikatRiveilla;
    }

    public int getRivit() {
        return rivit;
    }

    public int getPaikatRiveilla() {
        return paikatRiveilla;
    }

}
