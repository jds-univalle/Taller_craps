package com.example.crapsgame262.models;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Dice dice1;
    private final Dice dice2;
    private final List<Integer> historial = new ArrayList<>();


    private int throwValue;
    private int point;
    private int gamesWon;
    private int gamesLost;
    private boolean pointEstablished;
    private String message;

    public Game() {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.throwValue = 0;
        this.point = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.pointEstablished = false;
        this.message = "Lanza los dados para iniciar el juego.";
    }

    /**
     * Rolls both dice and applies the craps rules to the resulting value.
     */
    public void rollDice() {
        int value1 = dice1.roll();
        int value2 = dice2.roll();
        throwValue = value1 + value2;
        historial.add(throwValue);

        if (pointEstablished) {
            playPointRoll();
        } else {
            playComeOutRoll();
        }
    }

    /**
     * Come-out roll: a natural (7 or 11) wins, craps (2, 3 or 12) loses,
     * any other value establishes the point.
     */
    private void playComeOutRoll() {
        if (throwValue == 7 || throwValue == 11) {
            gamesWon++;
            message = "Tiro de salida: " + throwValue + ". Natural! Ganaste el juego.";
        } else if (throwValue == 2 || throwValue == 3 || throwValue == 12) {
            gamesLost++;
            message = "Tiro de salida: " + throwValue + ". Craps! Perdiste el juego.";
        } else {
            point = throwValue;
            pointEstablished = true;
            message = "Tiro de salida: " + throwValue + ". Punto establecido en " + point + ".";
        }
    }

    /**
     * Following rolls: matching the point wins, rolling a 7 loses.
     */
    private void playPointRoll() {
        if (throwValue == point) {
            gamesWon++;
            message = "Tiro: " + throwValue + ". Repetiste el punto " + point + ". Ganaste el juego!";
            pointEstablished = false;
            point = 0;
        } else if (throwValue == 7) {
            gamesLost++;
            message = "Tiro: " + throwValue + ". Siete fuera! Perdiste el juego, el punto era " + point + ".";
            pointEstablished = false;
            point = 0;
        } else {
            message = "Tiro: " + throwValue + ". Sigue lanzando, el punto es " + point + ".";
        }
    }

    public List<Integer> getHistorial() {
        return historial;
    }

    public String getHistorialTexto() {
        return "Tiros: " + historial;
    }

    public Dice getDice1() {
        return dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public int getThrowValue() {
        return throwValue;
    }

    public int getPoint() {
        return point;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public boolean isPointEstablished() {
        return pointEstablished;
    }

    public String getMessage() {
        return message;
    }

    public String getSummary() {
        return "Ganados: " + gamesWon + " | Perdidos: " + gamesLost;

    }
}
