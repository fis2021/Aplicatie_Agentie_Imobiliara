package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BuyerManuController {

    @FXML
    public void SeeHouses() {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("seeHouses.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root, 600, 575));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void SeeSchedulingsHistory() {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("seeHistoryOfSchedulings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root, 600, 575));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}