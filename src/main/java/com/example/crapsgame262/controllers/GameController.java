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
    private Label throwLabel;

    @FXML
    private Label pointLabel;

    @FXML
    private Label gamesWonLabel;

    @FXML
    private Label gamesLostLabel;

    @FXML
    private TextArea resultTextArea;

    @FXML
    public void initialize() {
        updateView();
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
        game.rollDice();
        updateView();
    }

    private void updateView() {
        if (game.getThrowValue() > 0) {
            dice1ImageView.setImage(new Image(
                    getClass().getResourceAsStream(game.getDice1().getImagePath())));
            dice2ImageView.setImage(new Image(
                    getClass().getResourceAsStream(game.getDice2().getImagePath())));
        }

        throwLabel.setText(game.getThrowValue() > 0 ? String.valueOf(game.getThrowValue()) : "-");
        pointLabel.setText(game.isPointEstablished() ? String.valueOf(game.getPoint()) : "-");
        gamesWonLabel.setText(String.valueOf(game.getGamesWon()));
        gamesLostLabel.setText(String.valueOf(game.getGamesLost()));

        if (resultTextArea != null) {
            resultTextArea.setText(game.getMessage() + "\n" + game.getSummary() + "\n" + game.getHistorialTexto());
        }
    }
    @FXML
    void onActionHelpButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Instrucciones");
        alert.setHeaderText("Como jugar Craps");
        alert.setContentText("Lanzas dos dados.\n"
                + "Si en el primer tiro sacas 7 u 11, ganas.\n"
                + "Si sacas 2, 3 o 12 (Craps), pierdes.\n"
                + "Cualquier otro número (4, 5, 6, 8, 9, 10) es tu \"punto\" y sigues lanzando.\n"
                + "Si sacas tu punto antes de un 7, ganas; si sacas 7 antes, pierdes.");
        alert.showAndWait();
    }
}
