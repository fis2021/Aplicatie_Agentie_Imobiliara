package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.AddressAlreadyExistsException;
import org.loose.fis.sre.services.HouseService;

public class AddHouseController {

    @FXML
    private Text addHouseMessage;
    @FXML
    private TextField Address;
    @FXML
    private TextField Size;
    @FXML
    private TextField Rooms;

    @FXML
    public void handleAddHouseAction(){

        try
        {
            HouseService.addHouse(Address.getText(),Size.getText(),Rooms.getText());
            addHouseMessage.setText("House added successfully!");
        }
        catch (AddressAlreadyExistsException e) {
            addHouseMessage.setText(e.getMessage());
        }
    }
}