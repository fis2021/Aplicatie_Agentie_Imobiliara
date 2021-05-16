package org.loose.fis.sre.controllers;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.loose.fis.sre.model.House;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.HouseDoesNotExistsException;
import org.loose.fis.sre.services.HouseService;


public class DeleteHouseController {

    @FXML
    private Text deletehouseMessage;
    @FXML
    private TextField Address;


    @FXML
    public void handleDeleteHouseAction(){

        try
        {
            HouseService.deleteHouse(Address.getText());
            deletehouseMessage.setText("House  successfully deleted!");
        }
        catch (HouseDoesNotExistsException e) {
            deletehouseMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleSearchHouseAction(){

        try
        {
            deletehouseMessage.setText(HouseService.searchHouse(Address.getText()));
        }
        catch (HouseDoesNotExistsException e) {
            deletehouseMessage.setText(e.getMessage());
        }
    }
}