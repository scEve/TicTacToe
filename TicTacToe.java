package com.tictactoe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * @author: Eva Schlai
 * @name: TicTacToe
 * @date: 27.11.2021
 */

final class TicTacToe {

  private static String aktuellerSpieler;
  private static String[] feld;
  static final int ersteZeileErsteSpalte = 0;
  static final int ersteZeileZweiteSpalte = 1;
  static final int ersteZeileDritteSpalte = 2;
  static final int zweiteZeileErsteSpalte = 3;
  static final int zweiteZeileZweiteSpalte = 4;
  static final int zweiteZeileDritteSpalte = 5;
  static final int dritteZeileErsteSpalte = 6;
  static final int dritteZeileZweiteSpalte = 7;
  static final int dritteZeileDritteSpalte = 8;
  static final int anzahlFelder = 9;
  static final int anzahlZeilenSpalten = 3;
  static final int anzahlGewinnmoeglichkeiten = 8;
  static final int naechstesZeilenelement = 1;
  static final int naechstesSpaltenelement = 3;

  private TicTacToe() {
    // Throw an exception if this ever *is* called
    throw new AssertionError("Instantiating utility class.");
  }

  public static void main(final String[] args) {
    Scanner eingabe = new Scanner(System.in);
    feld = new String[anzahlFelder];
    aktuellerSpieler = "X";
    String gewinner = null;
    feldDefinieren();
    System.out.println("Willkommen zum TicTacToe-Spiel!");
    feldZeichnen();
    System.out.println("Spieler " + aktuellerSpieler + " beginnt. Feldnummer für die Platzierung von einem " + aktuellerSpieler + " eingeben: ");
    while (gewinner == null) {
      int eingabeBenutzer;
      try {
        eingabeBenutzer = eingabe.nextInt();
        if (!(eingabeBenutzer > 0 && eingabeBenutzer <= anzahlFelder)) {
          System.out.println("Ungültige Eingabe. Gib eine der dargestellten Feldnummern an: ");
          continue;
        }
      } catch (InputMismatchException exception) {
        System.out.println("Ungültige Eingabe. Gib eine der dargestellten Feldnummern an: ");
        continue;
      }
      if (feld[eingabeBenutzer - 1].equals(String.valueOf(eingabeBenutzer))) {
        feld[eingabeBenutzer - 1] = aktuellerSpieler;
        if (aktuellerSpieler.equals("X")) {
          aktuellerSpieler = "O";
        } else {
          aktuellerSpieler = "X";
        }
        feldZeichnen();
        gewinner = gewinnerPruefen();
      } else {
        System.out.println("Dieses Feld ist bereits belegt. Gib eine der dargestellten Feldnummern an: ");
      }
    }
    if (gewinner.equalsIgnoreCase("unentschieden")) {
      System.out.println("Unentschieden! Vielen Dank für das Spiel.");
    } else {
      System.out.println("Herzlichen Glückwunsch, Spieler " + gewinner + " hat gewonnen! Vielen Dank für das Spiel.");
    }
  }

  static String gewinnerPruefen() {
    String gewinner = null;
    String zeichenfolge = null;
    for (int betrachtetesFeld = 0; betrachtetesFeld < anzahlZeilenSpalten; betrachtetesFeld++) {
      zeichenfolge = feld[betrachtetesFeld] + feld[betrachtetesFeld + naechstesZeilenelement] + feld[betrachtetesFeld + (naechstesZeilenelement * 2)];
      gewinner = zeichenfolgePruefen(zeichenfolge, gewinner);
    }
    for (int betrachtetesFeld = 0; betrachtetesFeld < anzahlZeilenSpalten; betrachtetesFeld++) {
      zeichenfolge = feld[betrachtetesFeld] + feld[betrachtetesFeld + naechstesSpaltenelement] + feld[betrachtetesFeld + (naechstesSpaltenelement * 2)];
      gewinner = zeichenfolgePruefen(zeichenfolge, gewinner);
    }
    zeichenfolge = feld[ersteZeileErsteSpalte] + feld[zweiteZeileZweiteSpalte] + feld[dritteZeileDritteSpalte];
    gewinner = zeichenfolgePruefen(zeichenfolge, gewinner);
    zeichenfolge = feld[ersteZeileDritteSpalte] + feld[zweiteZeileZweiteSpalte] + feld[dritteZeileErsteSpalte];
    gewinner = zeichenfolgePruefen(zeichenfolge, gewinner);
    if ("X".equals(gewinner) || "O".equals(gewinner)) {
      return gewinner;
    }
    for (int feldIndex = 0; feldIndex < anzahlFelder; feldIndex++) {
      int defaultFeldInhalt = feldIndex + 1;
      if (Arrays.asList(feld).contains(String.valueOf(defaultFeldInhalt))) {
        break;
      } else if (feldIndex == anzahlGewinnmoeglichkeiten) {
        return "unentschieden";
      }
    }
    System.out.println("Spieler " + aktuellerSpieler + " ist an der Reihe. Feldnummer für Platzierung von einem " + aktuellerSpieler + " eingeben: ");
    return null;
  }

  static String zeichenfolgePruefen(final String zeichenfolge, final String aktuellerGewinner) {
    if (aktuellerGewinner == null) {
      if (zeichenfolge.equals("XXX")) {
        return "X";
      } else if (zeichenfolge.equals("OOO")) {
        return "O";
      }
    }
    return aktuellerGewinner;
  }

  static void feldDefinieren() {
    for (int feldIndex = 0; feldIndex < anzahlFelder; feldIndex++) {
      int defaultFeldInhalt = feldIndex + 1;
      feld[feldIndex] = String.valueOf(defaultFeldInhalt);
    }
  }

  static void feldZeichnen() {
    System.out.println(feld[ersteZeileErsteSpalte] + " | " + feld[ersteZeileZweiteSpalte] + " | " + feld[ersteZeileDritteSpalte]);
    System.out.println("---------");
    System.out.println(feld[zweiteZeileErsteSpalte] + " | " + feld[zweiteZeileZweiteSpalte] + " | " + feld[zweiteZeileDritteSpalte]);
    System.out.println("---------");
    System.out.println(feld[dritteZeileErsteSpalte] + " | " + feld[dritteZeileZweiteSpalte] + " | " + feld[dritteZeileDritteSpalte]);
  }
}
