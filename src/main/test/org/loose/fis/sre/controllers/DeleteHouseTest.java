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
class DeleteHouseTest {

    public static final String ADDRESS = "Address";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.HOUSE_FOLDER = ".test-see-deletehouse";
        FileSystemService.initDirectory_house();
        FileUtils.cleanDirectory(FileSystemService.getHouseHomeFolder().toFile());
        HouseService.initDatabase();
        HouseService.addHouse(ADDRESS,ADDRESS,ADDRESS);
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("deleteHouse.fxml"));
        primaryStage.setTitle("Agentie Imobiliara");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
    }

    @Test
    void TestAddHouse(FxRobot robot) {
        robot.clickOn("#address");
        robot.write(ADDRESS);
        robot.clickOn("#searchButton");
        assertThat(robot.lookup("#deletehouseMessage").queryText()).hasText(
                String.format("Address=Address, Size= Address, Rooms= Address"));
        robot.clickOn("#deleteButton");
        assertThat(robot.lookup("#deletehouseMessage").queryText()).hasText(
                String.format("House  successfully deleted!"));
        robot.clickOn("#deleteButton");
        assertThat(robot.lookup("#deletehouseMessage").queryText()).hasText(
                String.format("A house with the address %s does not exist!", ADDRESS));
    }
}