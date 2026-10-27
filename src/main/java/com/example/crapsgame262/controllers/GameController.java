package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

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
            resultadoTextArea.setText(game.getMensaje() + "\n" + game.getHistorialTexto());
        }

    }
  @FXML
     void onActionHelpButton(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Instrucciones");
         alert.setHeaderText("Cómo jugar Craps");
         alert.setContentText("Lanzas dos dados.\n"
                 + "Si en el primer tiro sacas 7 u 11, ganas.\n"
                 + "Si sacas 2, 3 o 12 (Craps), pierdes.\n"
                 + "Cualquier otro número (4, 5, 6, 8, 9, 10) es tu \"punto\" y sigues lanzando.\n"
                 + "Si sacas tu punto antes de un 7, ganas; si sacas 7 antes, pierdes.");
         alert.showAndWait();
      }
}
