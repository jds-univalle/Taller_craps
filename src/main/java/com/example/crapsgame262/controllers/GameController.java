package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private final Game game = new Game();

    private Player currentPlayer;

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label tiroLabel;

    @FXML
    private Label puntoLabel;

    @FXML
    private Label juegosGanadosLabel;

    @FXML
    private Label juegosPerdidosLabel;

    @FXML
    private TextArea resultadoTextArea;

    @FXML
    public void initialize() {
        actualizarVista();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        game.lanzarDados();
        actualizarVista();
    }

    private void actualizarVista() {
        if (game.getTiro() > 0) {
            dice1ImageView.setImage(new Image(
                    getClass().getResourceAsStream(game.getDice1().getImagePath())));
            dice2ImageView.setImage(new Image(
                    getClass().getResourceAsStream(game.getDice2().getImagePath())));
        }

        tiroLabel.setText(game.getTiro() > 0 ? String.valueOf(game.getTiro()) : "-");
        puntoLabel.setText(game.isPuntoDefinido() ? String.valueOf(game.getPunto()) : "-");
        juegosGanadosLabel.setText(String.valueOf(game.getJuegosGanados()));
        juegosPerdidosLabel.setText(String.valueOf(game.getJuegosPerdidos()));

        if (resultadoTextArea != null) {
            resultadoTextArea.setText(game.getMensaje());
        }
    }
}
