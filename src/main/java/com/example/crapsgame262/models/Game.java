package com.example.crapsgame262.models;

public class Game {

    private final Dice dice1;
    private final Dice dice2;

    private int tiro;
    private int punto;
    private int juegosGanados;
    private int juegosPerdidos;
    private boolean puntoDefinido;
    private String mensaje;

    public Game() {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.tiro = 0;
        this.punto = 0;
        this.juegosGanados = 0;
        this.juegosPerdidos = 0;
        this.puntoDefinido = false;
        this.mensaje = "Lanza los dados para iniciar el juego.";
    }

    /**
     * Realiza un tiro de los dos dados y aplica las reglas del craps.
     */
    public void lanzarDados() {
        int valor1 = dice1.roll();
        int valor2 = dice2.roll();
        tiro = valor1 + valor2;

        if (puntoDefinido) {
            jugarTiroDePunto();
        } else {
            jugarTiroDeSalida();
        }
    }

    /**
     * Primer tiro de cada juego: natural (7 u 11) gana, craps (2, 3 o 12) pierde,
     * cualquier otro valor establece el punto.
     */
    private void jugarTiroDeSalida() {
        if (tiro == 7 || tiro == 11) {
            juegosGanados++;
            mensaje = "Tiro de salida: " + tiro + ". Natural! Ganaste el juego.";
        } else if (tiro == 2 || tiro == 3 || tiro == 12) {
            juegosPerdidos++;
            mensaje = "Tiro de salida: " + tiro + ". Craps! Perdiste el juego.";
        } else {
            punto = tiro;
            puntoDefinido = true;
            mensaje = "Tiro de salida: " + tiro + ". Punto establecido en " + punto + ".";
        }
    }

    /**
     * Tiros posteriores: repetir el punto gana, sacar 7 pierde.
     */
    private void jugarTiroDePunto() {
        if (tiro == punto) {
            juegosGanados++;
            mensaje = "Tiro: " + tiro + ". Repetiste el punto " + punto + ". Ganaste el juego!";
            puntoDefinido = false;
            punto = 0;
        } else if (tiro == 7) {
            juegosPerdidos++;
            mensaje = "Tiro: " + tiro + ". Siete fuera! Perdiste el juego, el punto era " + punto + ".";
            puntoDefinido = false;
            punto = 0;
        } else {
            mensaje = "Tiro: " + tiro + ". Sigue lanzando, el punto es " + punto + ".";
        }
    }

    public Dice getDice1() {
        return dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    public int getJuegosGanados() {
        return juegosGanados;
    }

    public int getJuegosPerdidos() {
        return juegosPerdidos;
    }

    public boolean isPuntoDefinido() {
        return puntoDefinido;
    }

    public String getMensaje() {
        return mensaje;
    }
}
