package org.loose.fis.sre.services;

import static org.junit.jupiter.api.Assertions.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.HouseService;
import org.loose.fis.sre.model.House;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HouseServiceTest {

    public static final String ADDRESS="Address";
    public static final String SIZE="Size";
    public static final String ROOMS="Rooms";
    public static final String MOD="Modify";

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @BeforeAll
    static void beforeAll() throws Exception{
        FileSystemService.HOUSE_FOLDER = ".test-addhouses";
        FileSystemService.initDirectory_house();
        FileUtils.cleanDirectory(FileSystemService.getHouseHomeFolder().toFile());
        HouseService.initDatabase();
        System.out.println("Before class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After class");
    }

    @Test
    @Order(1)

    @DisplayName("Database is initialized and there are no houses")
    void testDataBaseIsInitializedAndNoHousesIsPersisted() {
        assertThat(HouseService.getAllHouses()).isNotNull();
        assertThat(HouseService.getAllHouses()).isEmpty();
        System.out.println("1");
    }

    @Test
    @Order(2)

    @DisplayName("House is successfully persisted to Database")
    void testHouseIsAddedToDatabase() throws AddressAlreadyExistsException {
        HouseService.addHouse(ADDRESS, SIZE, ROOMS);
        assertThat(HouseService.getAllHouses()).isNotEmpty();
        assertThat(HouseService.getAllHouses()).size().isEqualTo(1);
        House house = HouseService.getAllHouses().get(0);
        assertThat(house).isNotNull();
        assertThat(house.getAddress()).isEqualTo(ADDRESS);
        assertThat(house.getSize()).isEqualTo(SIZE);
        assertThat(house.getRooms()).isEqualTo(ROOMS);
        System.out.println("2");
    }

    @Test
    @Order(3)

    @DisplayName("House can not be added twice")
    void testHouseCanNotBeAddedTwice() {
        assertThrows(AddressAlreadyExistsException.class, () -> {
            HouseService.addHouse(ADDRESS, SIZE, ROOMS);
            HouseService.addHouse(ADDRESS, SIZE, ROOMS);
        });
        System.out.println("3");
    }

    @Test
    @Order(4)

    @DisplayName("Address must be unique")
    void testAddressDoesNotAlreadyExist() {
        assertThrows(AddressAlreadyExistsException.class, () -> {
            HouseService.addHouse(ADDRESS, SIZE, ROOMS);
            HouseService.checkAddressDoesNotAlreadyExist(ADDRESS);
        });
        System.out.println("4");
    }

    @Test
    @Order(5)

    @DisplayName("House list is correct")
    void testSeeHouses()  {
        assertThat(HouseService.seeHouses()).isEqualTo("Address=Address, Size= Size, Rooms= Rooms\n");
    }

    @Test
    @Order(6)

    @DisplayName("House can be edited")
    void testEditHouse() throws HouseDoesNotExistsException {
        HouseService.editHouse(ADDRESS,MOD,MOD);
        House house = HouseService.getAllHouses().get(0);
        org.assertj.core.api.Assertions.assertThat(house.getAddress()).isEqualTo(ADDRESS);
        org.assertj.core.api.Assertions.assertThat(house.getSize()).isEqualTo(MOD);
        org.assertj.core.api.Assertions.assertThat(house.getRooms()).isEqualTo(MOD);
        System.out.println("6");
    }

    @Test
    @Order(7)

    @DisplayName("House can't be edited because it is not found")
    void testHouseEditNotFound() {
        assertThrows(HouseDoesNotExistsException.class, () -> {
            HouseService.editHouse("address",MOD,MOD);
            HouseService.checkAddressDoesExist("address");
        });
        System.out.println("7");
    }

    @Test
    @Order(8)

    @DisplayName("House is found")
    void testHouseIsFound() throws HouseDoesNotExistsException {
        assertThat(HouseService.searchHouse(ADDRESS)).isEqualTo("Address=Address, Size= Modify, Rooms= Modify");
        System.out.println("8");
    }

    @Test
    @Order(9)

    @DisplayName("House is not found")
    void testHouseIsNotFound(){
        assertThrows(HouseDoesNotExistsException.class, () -> {
            HouseService.checkAddressDoesExist("address");
        });
        System.out.println("9");
    }

    @Test
    @Order(10)

    @DisplayName("House exists")
    void checkAddressDoesExistTest()  {
        House house = HouseService.getAllHouses().get(0);
        assertThat(house).isNotNull();
        assertThat(house.getAddress()).isEqualTo(ADDRESS);
        System.out.println("10");
    }

    @Test
    @Order(11)

    @DisplayName("House does not exists")
    void  checkAddressDoesNotExistTest() {
        assertThrows(HouseDoesNotExistsException.class, () -> {
            HouseService.checkAddressDoesExist("notADDRESS");
        });
        System.out.println("11");
    }
    @Test
    @Order(12)

    @DisplayName("House deleted successfully")
    void testDeleteHouse() throws HouseDoesNotExistsException{
        HouseService.deleteHouse(ADDRESS);
        assertThat(HouseService.getAllHouses()).isEmpty();
        System.out.println("12");
    }

    @Test
    @Order(13)

    @DisplayName("House that must be deleted is not found")
    void testNotFoundDeleteHouse(){
        assertThrows(HouseDoesNotExistsException.class, () -> {
            HouseService.checkAddressDoesExist(ADDRESS);
        });
        System.out.println("13");
    }


}

