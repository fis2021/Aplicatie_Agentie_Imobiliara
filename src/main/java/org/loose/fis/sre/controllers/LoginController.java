package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.IncorrectCredentials;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class LoginController {

    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Buyer", "Agent");
    }

    @FXML
    public void handleLoginAction() {
        try {
            Parent root;
            int ok=UserService.CheckUserCredentials(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            loginMessage.setText("Login succeeded!");
            if(ok==1)
            {
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("buyerMenu.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Agentie Imobiliara");
                    stage.setScene(new Scene(root, 600, 575));
                    stage.show();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            else if(ok==2)
            {
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("agentMenu.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Agentie Imobiliara");
                    stage.setScene(new Scene(root, 600, 575));
                    stage.show();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        } catch (IncorrectCredentials e) {
            loginMessage.setText(e.getMessage());
        }
    }
}
