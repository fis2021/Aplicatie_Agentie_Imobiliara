package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.SchedulingAlreadyExistsException;
import org.loose.fis.sre.exceptions.IncorrectNameException;
import org.loose.fis.sre.exceptions.IncorrectDateException;
import org.loose.fis.sre.exceptions.HouseDoesNotExistsException;
import org.loose.fis.sre.exceptions.AgentDoesNotExistException;
import org.loose.fis.sre.services.SchedulingService;
import org.loose.fis.sre.services.UserService;

public class SchedulingController {
    @FXML
    private Text makeSchedulingMessage;
    @FXML
    private Text agentsMessage;
    @FXML
    private TextField address;
    @FXML
    private TextField special_req;
    @FXML
    private ChoiceBox day;
    @FXML
    private ChoiceBox hour;
    @FXML
    private ChoiceBox month;
    @FXML
    private ChoiceBox year;
    @FXML
    private TextField agentSchedule;
    @FXML
    private TextField username;

    @FXML
    public void initialize() {
        day.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31");
        month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        year.getItems().addAll("2021","2022");
        hour.getItems().addAll("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");
    }

    @FXML
    public void handleSchedulingAction(){
        try
        {
            SchedulingService.addScheduling(address.getText(),(String) day.getValue(),(String) month.getValue(),(String) year.getValue(),(String) hour.getValue(),
                    agentSchedule.getText(),special_req.getText(),username.getText());
            makeSchedulingMessage.setText("Scheduling saved successfully!");
        }
        catch (SchedulingAlreadyExistsException e) {
            makeSchedulingMessage.setText(e.getMessage());
       }
        catch (IncorrectNameException e) {
            makeSchedulingMessage.setText(e.getMessage());
        }
        catch (HouseDoesNotExistsException e) {
            makeSchedulingMessage.setText(e.getMessage());
        }
        catch (IncorrectDateException e) {
            makeSchedulingMessage.setText(e.getMessage());
        }
        catch (AgentDoesNotExistException e) {
            makeSchedulingMessage.setText(e.getMessage());
        }
    }
    @FXML
    public void handleSeeAgents() {
        agentsMessage.setText(UserService.agents_lsit());
    }
}