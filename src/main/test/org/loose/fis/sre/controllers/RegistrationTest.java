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
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class RegistrationTest {

    public static final String ADMIN = "user";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-see-registration";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Agentie Imobiliara");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
    }

    @Test
    void testRegistration(FxRobot robot) {
        robot.clickOn("#fullName");
        robot.write(ADMIN);
        robot.clickOn("#phone");
        robot.write(ADMIN);
        robot.clickOn("#username");
        robot.write(ADMIN);
        robot.clickOn("#password");
        robot.write(ADMIN);
        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

    }
}