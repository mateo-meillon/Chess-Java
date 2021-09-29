package de.matev3.chess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Scanner scanner;
    private ArrayList<Figure> taken = new ArrayList<>();
    private int roundNumber = 1;
    private boolean white = true;
    private Figure[][] field = new Figure[][]{
            {new Figure("♜", "black"), new Figure("♞", "black"), new Figure("♝", "black"), new Figure("♛", "black"), new Figure("♚", "black"), new Figure("♝", "black"), new Figure("♞", "black"), new Figure("♜", "black")},
            {new Figure("♟", "black"), new Figure("♟", "black"), new Figure("♟", "black"), new Figure("♟", "black"), new Figure("♟", "black"), new Figure("♟", "black"), new Figure("♟", "black"), new Figure("♟", "black")},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Figure("♙", "white"), new Figure("♙", "white"), new Figure("♙", "white"), new Figure("♙", "white"), new Figure("♙", "white"), new Figure("♙", "white"), new Figure("♙", "white"), new Figure("♙", "white")},
            {new Figure("♖", "white"), new Figure("♘", "white"), new Figure("♗", "white"), new Figure("♕", "white"), new Figure("♔", "white"), new Figure("♗", "white"), new Figure("♘", "white"), new Figure("♖", "white")}
    };

    public void start() {
        printField();
        nextMove();
    }

    public void stop() {

    }

    public void nextMove() {
        scanner = new Scanner(System.in);
        if (roundNumber < 2)
            System.out.println("\n\nNext Move (e.g. 'e7 to e5'):");
        else
            System.out.println("\n\nNext Move:");
        String from = scanner.next(); scanner.next();
        String to = scanner.next();

        int fIndex = 0;
        int tIndex = 0;
        char[] alph = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (int i = 0; i < 8; i++) {
            if(alph[i] == from.charAt(0))
                fIndex = i;
            if(alph[i] == to.charAt(0))
                tIndex = i;
        }

        int getRowF = Integer.parseInt(from.substring(1, 2));
        Figure[] figuresT = field[getRowF - 1];
        Figure savedPorn = figuresT[fIndex];

        int getRowT = Integer.parseInt(to.substring(1, 2));
        Figure[] figuresF = field[getRowT - 1];

        if (figuresF[tIndex] == null || figuresF[tIndex].color != savedPorn.color) {
            if (figuresF[tIndex] != null)
                if (figuresF[tIndex].color != savedPorn.color)
                    taken.add(figuresF[tIndex]);
            figuresT[fIndex] = null;
            figuresF[tIndex] = savedPorn;
        }

        white = !white;
        roundNumber++;
        printField();
        nextMove();
    }

    public void printField() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(Color.WHITE + "\tTurn number " + roundNumber);
        if (white)
            System.out.println(Color.RESET + "\tWhite's turn\n");
        else
            System.out.println(Color.RESET + "\tBlack's turn\n");
        String takenW = "";
        String takenB = "";
        for (Figure figure : taken) {
            if (figure.color == "black")
                takenB = takenB + Color.PURPLE + figure.kuerzel + " ";
            else
                takenW = takenB + Color.RED + figure.kuerzel + " ";
        }
        // System.out.println(takenB + Color.RESET + " |  " + takenW);
        // System.out.println(Color.BLACK + "");
        System.out.println(Color.CYAN + "\t\t a\t b\t c\t d\t e\t f\t g\t h");
        System.out.println(Color.BLACK + "\t");
        int at = 0;
        if (!white)
            at = 7;
        for (int i = 0; i < 8; i++) {
            Figure[] figures = field[at];
            String row = "\t" +Color.CYAN + (at + 1) + Color.BLACK + "\t";
            for (Figure figure : figures) {
                if (figure != null) {
                    if (figure.color == "black")
                        row = row + " " + Color.PURPLE + figure.kuerzel + Color.BLACK + "\t";
                    else
                        row = row + " " + Color.RED + figure.kuerzel + Color.BLACK + "\t";
                }
                else
                    row = row + Color.BLACK + " ░\t";
            }
            System.out.println(row);
            System.out.println(Color.BLACK + "\t");
            if (white)
                at++;
            else
                at--;
        }
        System.out.println(Color.CYAN + "\t\t a\t b\t c\t d\t e\t f\t g\t h");
    }

}
