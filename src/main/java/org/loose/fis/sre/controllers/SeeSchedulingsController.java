package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import org.loose.fis.sre.exceptions.*;

import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import org.loose.fis.sre.services.SchedulingService;
public class SeeSchedulingsController {

    @FXML
    private Text editScheduling;
    @FXML
    private Text seeScheduleMessage;
    @FXML
    private TextField Name;
    @FXML
    private ChoiceBox Day;
    @FXML
    private ChoiceBox Hour;
    @FXML
    private ChoiceBox Month;
    @FXML
    private ChoiceBox Year;
    @FXML
    private TextField Reason;

    @FXML
    public void initialize() {
        Day.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31");
        Month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        Year.getItems().addAll("2021","2022");
        Hour.getItems().addAll("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");
    }

    @FXML
    public void handleSeeSchedulings() {
        try {

            seeScheduleMessage.setText(SchedulingService.seeSchedulings(Name.getText()));
        } catch (NoSchedulingsException e) {
            seeScheduleMessage.setText(e.getMessage());
        }

    }
    public void handleApprove()
    {
        try{
            SchedulingService.approveScheduling(Name.getText(),(String) Hour.getValue(),(String) Day.getValue(),(String) Month.getValue(),(String) Year.getValue());
            editScheduling.setText("Scheduling approved!");
        }
        catch (IncorrectDateException e) {
            editScheduling.setText(e.getMessage());
        }
        catch (AgentDoesNotExistException e) {
            editScheduling.setText(e.getMessage());
        }
        catch (SchedulingNotFoundException e) {
            editScheduling.setText(e.getMessage());
        }

    }
    public void handleReject()
    {
        try{
            SchedulingService.rejectScheduling(Name.getText(),(String) Hour.getValue(),(String) Day.getValue(),(String) Month.getValue(),(String) Year.getValue(), Reason.getText());
            editScheduling.setText("Scheduling rejected!");
        }
        catch (IncorrectDateException e) {
            editScheduling.setText(e.getMessage());
        }
        catch (AgentDoesNotExistException e) {
            editScheduling.setText(e.getMessage());
        }
        catch (SchedulingNotFoundException e) {
            editScheduling.setText(e.getMessage());
        }

    }

}



