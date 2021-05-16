package org.loose.fis.sre.controllers;

import static org.junit.jupiter.api.Assertions.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.SchedulingService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.HouseService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class ScheduleVisitTest {

    public static final String AGENT="Agent";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.SCHEDULING_FOLDER = ".test-see-booking";
        FileSystemService.initDirectoryScheduling();
        FileUtils.cleanDirectory(FileSystemService.getSchedulingHomeFolder().toFile());
        SchedulingService.initDatabase();
        FileSystemService.APPLICATION_FOLDER = ".test-see-user";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser(AGENT,AGENT,AGENT,AGENT,AGENT);
        FileSystemService.HOUSE_FOLDER = ".test-see-edithouseb";
        FileSystemService.initDirectory_house();
        FileUtils.cleanDirectory(FileSystemService.getHouseHomeFolder().toFile());
        HouseService.initDatabase();
    }
    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("schedule.fxml"));
        primaryStage.setTitle("Agentie Imobiliara");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
    }
    @Test
    void TestBooking(FxRobot robot) {
        robot.clickOn("#bookingButton");
        assertThat(robot.lookup("#bookingMessage").queryText()).hasText(
                String.format("An agent with the name  does not exist!"));
        robot.clickOn("#agent_book");
        robot.write(AGENT);
        robot.clickOn("#bookingButton");
        assertThat(robot.lookup("#bookingMessage").queryText()).hasText(
                String.format("The name  is incorrect!"));
        robot.clickOn("#name");
        robot.write(AGENT);
        robot.clickOn("#bookingButton");
        assertThat(robot.lookup("#bookingMessage").queryText()).hasText(
                String.format("A house with the address  does not exist!"));
        robot.clickOn("#seeAgentsButton");
        assertThat(robot.lookup("#agentsMessage").queryText()).hasText(
                String.format("fullName='Agent', phoneNumber='Agent'\n"));
    }
}
