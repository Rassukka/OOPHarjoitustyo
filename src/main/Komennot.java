package main;

import java.time.LocalDate;
import java.util.Scanner;

public class Komennot {

    private Scanner scanner = new Scanner(System.in);
    private Database db = Database.getInstance();

    //Komennot luokka, kutsutaan main methodista (ei paras vaihtoehto varmaankaan tehdä näin???) lisää uusi komento case:na ja kirjoita mitä komento tekee
    //Muista lisätä myös help methodiin komento, ja mitä se tekee.

    public Komennot() {
        System.out.println("Elokuvateatterijärjestelmä, saatavilla olevat komennot 'help' komennolla, poistu kirjoittamalla 0");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "0":
                    System.out.println("Please com agen!");
                    System.exit(0);
                    break;

                case "help":
                    help();
                    break;

                case "salit":
                    System.out.println("Elokuvateatterissa olevat salit");
                    db.printSalit();
                    break;

                case "elokuvat":
                    System.out.println("Elokuvat:");
                    db.printElokuvat();
                    break;

                case "varaa":
                    System.out.println("Tällä hetkellä ohjelmistossa olevat elokuvat:");
                    break;

                //Siirtyy jäsrjestelmänvalvoja tilaan.
                case "admin123":
                    System.out.println("Järjestelmänvalvoja tila päällä.");
                    boolean adminMode = true;

                    while (adminMode) {
                        System.out.print("> ");
                        String inputA = scanner.nextLine().toLowerCase();

                        switch (inputA) {
                            case "0":
                                System.out.println("Siirrytään takaisin asiakas moodiin.");
                                adminMode = false;
                                break;

                            case "help":
                                helpAdmin();
                                break;

                            case "salit":
                                System.out.println("Elokuvateatterissa olevat salit");
                                db.printSalit();
                                break;

                            case "lisääsali":
                                System.out.println("anna tiedot salille: (salin numero, rivien määrä, paikkojen määrä riveillä");
                                System.out.print("> ");
                                String[] tiedot = scanner.nextLine().toLowerCase().split(" ");
                                try {
                                    Sali sali = new Sali(Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]), Integer.parseInt(tiedot[2]));
                                    db.lisaaSali(sali);
                                } catch (Exception e) {
                                    //TODO: Pitäisiko tähän lisätä looppi, jotta saa yrittää uudelleen niin kauan kun tiedot menee oikein, tai poistua kirjoittamalla 0?
                                    System.err.println("Virheelliset tiedot...");
                                    break;
                                }
                                System.out.println("Sali lisätty onnistuneesti!");
                                break;

                            case "poistasali":
                                System.out.println("Anna salin numero jonka haluat poistaa");
                                System.out.print("> ");
                                try {
                                    String poista = scanner.nextLine();
                                    db.poistaSali(Integer.parseInt(poista));
                                } catch (Exception e) {
                                    System.err.println("Anna numero!");
                                }
                                break;

                            case "lisääelokuva":
                                String[] elotiedot = new String[9];

                                System.out.print("Anna elokuvan nimi: (merkkijono)"+"\n"+"> ");
                                elotiedot[0] = scanner.nextLine();
                                System.out.print("Anna elokuvan ikäraja: (numero)"+"\n"+"> ");
                                elotiedot[4] = scanner.nextLine();
                                System.out.print("Anna elokuvan päähenkilön nimi: (merkkijono)"+"\n"+"> ");
                                elotiedot[3] = scanner.nextLine();
                                System.out.print("Onko elokuva 3D?: (true/false)"+"\n"+"> ");
                                elotiedot[2] = scanner.nextLine();
                                System.out.println("anna elokuvan tyyppi: (TOIMINTA, SEIKKAILU, ROMANTIIKKA, JANNITYS,\n" +
                                        "    ANIMAATIO, KLASSIKKO, KAUHU, KOMEDIA,\n" +
                                        "    KOTIMAINEN, FANTASIA tai DRAAMA)");
                                System.out.print("> ");
                                elotiedot[1] = scanner.nextLine().toUpperCase();
                                System.out.print("Anna ensi-ilta: (yyyy-mm-dd) (esim: 2014-05-01)"+"\n"+"> ");
                                elotiedot[5] = scanner.nextLine();
                                System.out.print("Anna viimeinen näytös päivä: (yyyy-mm-dd) (esim: 2014-05-01)"+"\n"+"> ");
                                elotiedot[7] = scanner.nextLine();
                                System.out.print("Salinumero: (numero)"+"\n"+"> ");
                                elotiedot[6] = scanner.nextLine();
                                System.out.print("Anna näytösaika: (merkkijono)"+"\n"+"> ");
                                elotiedot[8] = scanner.nextLine();

                                try {
                                    Elokuva elokuva = new Elokuva(elotiedot[0], Tyyppi.valueOf(elotiedot[1]), Boolean.parseBoolean(elotiedot[2]),
                                            elotiedot[3], Integer.parseInt(elotiedot[4]), elotiedot[5], Integer.parseInt(elotiedot[6]),
                                            LocalDate.parse(elotiedot[7]), elotiedot[8]);
                                    db.lisaaElokuva(elokuva);
                                } catch (Exception e) {
                                    System.err.println("Virheelliset tiedot...");
                                    break;
                                }
                                System.out.println("Elokuva lisätty onnistuneesti!");
                                break;

                            default:
                                System.out.println("Virheellinen komento");
                        }
                    }
                    break; //Järjestelmävalvojatilan break, tämän jälkeen ollaan taas asiakas moodissa.

                default:
                    System.out.println("Virheellinen komento");

            }
        }
    }

    //Metodi, joka tulostaa ohjeet, \n vaihtaa riviä
    public void help() {
        System.out.println();
        System.out.println("Komennot: \n" +
                "0: poistu ohjelmasta \n" +
                "salit: printtaa saatavilla olevat salit \n" +
                "varaa: varaa elokuviin lippuja, sekä paikkoja \n");
    }
    public void helpAdmin() {
        System.out.println();
        System.out.println("Komennot: \n" +
                "0: poistu asiakastilaan \n" +
                "lisääsali: lisää sali elokuvateatteriin \n" +
                "poistasali: poistaa salin elokuvateatterista \n" +
                "salit: printtaa saatavilla olevat salit \n" +
                "lisääelokuva: lisää elokuvia \n ");
    }
}
