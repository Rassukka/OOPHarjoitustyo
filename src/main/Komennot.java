package main;

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

                case "printsalit":
                    System.out.println("Elokuvateatterissa olevat salit");
                    db.printSalit();
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

                            //TODO: funkar inte, korjaan myöhemmin, ei jaksa enää tänään.
                       /* case "poistasali":
                            System.out.println("Anna salin numero jonka haluat poistaa");
                            System.out.print("> ");
                            try {
                                int poista = scanner.nextInt();
                                db.poistaSali(poista);
                            } catch (Exception e) {
                                System.out.println("Anna numero!");
                            }
                            break;*/

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
                "0: poistu ohjelmasta \n");
    }
    public void helpAdmin() {
        System.out.println();
        System.out.println("Komennot: \n" +
                "0: poistu asiakastilaan \n" +
                "lisääsali: lisää sali elokuvateatteriin \n" +
                "printsalit: printtaa saatavilla olevat salit \n");
    }
}
