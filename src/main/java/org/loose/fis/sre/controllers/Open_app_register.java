package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Open_app_register {
    @FXML
    public void handleOpenRegister()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root,600,575));
            stage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleOpenLogin()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root,600,575));
            stage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
