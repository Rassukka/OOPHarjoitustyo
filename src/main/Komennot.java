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

                case "printsalit":
                    System.out.println("Elokuvateatterissa olevat salit");
                    db.printSalit();
                    break;

                case "varaa":
                    System.out.println("Tällä hetkellä ohjelmistossa olevat elokuvat:");
                    db.printElokuvat();
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

                            case "poistasali":
                                System.out.println("Anna salin numero jonka haluat poistaa");
                                System.out.print("> ");
                                try {
                                    int poista = scanner.nextInt();
                                    db.poistaSali(poista);
                                } catch (Exception e) {
                                    System.out.println("Anna numero!");
                                }
                                //TODO: Jostain syystä break ei toimi, vaan heittää myös "virheellinen komento" viestin
                                break;

                            case "lisääelokuva": //TODO: tää on aika paska
                                String[] elotiedot = new String[9];

                                System.out.println("erota parametrit pilkulla ja välilyönnillä");
                                System.out.println("anna elokuvan nimi: (merkkijono)");
                                System.out.print("> ");
                                elotiedot[0] = scanner.nextLine();

                                System.out.println("anna elokuvan ikäraja(numero), päähenkilö(merkkijono) ja onko elokuva 3D(true/false)");
                                System.out.print("> ");
                                String elotiedot2[] = scanner.nextLine().split(", ");
                                elotiedot[2] = elotiedot2[2];
                                elotiedot[3] = elotiedot2[1];
                                elotiedot[4] = elotiedot2[0];

                                System.out.println("anna elokuvan tyyppi: (TOIMINTA, SEIKKAILU, ROMANTIIKKA, JANNITYS,\n" +
                                        "    ANIMAATIO, KLASSIKKO, KAUHU, KOMEDIA,\n" +
                                        "    KOTIMAINEN, FANTASIA tai DRAAMA)");
                                System.out.print("> ");
                                elotiedot[1] = scanner.nextLine().toUpperCase();

                                System.out.println("anna elokuvan ensi-ilta sekä viimeinen näytöspäivä (yyyy-mm-dd), salinumero ja näytösaika(merkkijono)");
                                System.out.print("> ");
                                String elotiedot3[] = scanner.nextLine().split(", ");
                                elotiedot[5] = elotiedot3[0];
                                elotiedot[6] = elotiedot3[2];
                                elotiedot[7] = elotiedot3[1];
                                elotiedot[8] = elotiedot3[3];

                                try {
                                    Elokuva elokuva = new Elokuva(elotiedot[0], Tyyppi.valueOf(elotiedot[1]), Boolean.parseBoolean(elotiedot[2]), elotiedot[3], Integer.parseInt(elotiedot[4]), elotiedot[5], Integer.parseInt(elotiedot[6]), LocalDate.parse(elotiedot[7]), elotiedot[8]);
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
                "varaa: varaa elokuviin lippuja, sekä paikkoja \n");
    }
    public void helpAdmin() {
        System.out.println();
        System.out.println("Komennot: \n" +
                "0: poistu asiakastilaan \n" +
                "lisääsali: lisää sali elokuvateatteriin \n" +
                "printsalit: printtaa saatavilla olevat salit \n" +
                "lisääelokuva: lisää elokuvia \n ");
    }
}