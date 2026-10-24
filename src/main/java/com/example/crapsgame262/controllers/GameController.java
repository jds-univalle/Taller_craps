package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.Dice;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class GameController {

    private Player currentPlayer;

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        Dice dice1 = new Dice();
        dice1.roll();

        dice1ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice1.getImagePath()
                        )
                )
        );

        Dice dice2 = new Dice();
        dice2.roll();

        dice2ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice2.getImagePath()
                        )
                )
        );
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
