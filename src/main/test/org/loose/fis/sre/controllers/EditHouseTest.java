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
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.HouseService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class EditHouseTest {

    public static final String HOUSE = "house";
    public static final String MOD = "Modify";
    public static final String NOT_HOUSE = "house1";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.HOUSE_FOLDER = ".test-see-edithouse";
        FileSystemService.initDirectory_house();
        FileUtils.cleanDirectory(FileSystemService.getHouseHomeFolder().toFile());
        HouseService.initDatabase();
        HouseService.addHouse(HOUSE,HOUSE,HOUSE);
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("editHouse.fxml"));
        primaryStage.setTitle("Agentie Imobiliara");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
    }

    @Test
    void TestEditHouse(FxRobot robot) {
        robot.clickOn("#address");
        robot.write(HOUSE);
        robot.clickOn("#size");
        robot.write(MOD);
        robot.clickOn("#rooms");
        robot.write(MOD);
        robot.clickOn("#editButton");
        assertThat(robot.lookup("#edithouseMessage").queryText()).hasText(
                String.format("Changes saved successfully!"));
        robot.clickOn("#searchButton");
        assertThat(robot.lookup("#edithouseMessage").queryText()).hasText(
        String.format("Address=house, Size= Modify, Rooms= Modify"));
        robot.clickOn("#address");
        robot.write("1");
        robot.clickOn("#editButton");
        assertThat(robot.lookup("#edithouseMessage").queryText()).hasText(
                String.format("A house with the address %s does not exist!", NOT_HOUSE));
    }
}