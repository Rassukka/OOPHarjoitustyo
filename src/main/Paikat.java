package main;

public class Paikat {

    private String paikka;
    private boolean vapaa;

    public Paikat(String paikka, boolean vapaa) {
        this.paikka = paikka;
        this.vapaa = vapaa;
    }

    public String getPaikka() {
        return paikka;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public boolean isVapaa() {
        return vapaa;
    }

    public void setVapaa(boolean vapaa) {
        this.vapaa = vapaa;
    }

    public String stringVapaa() {
        if(vapaa) {
            return "true";
        } else {
            return "false";
        }
    }
}
