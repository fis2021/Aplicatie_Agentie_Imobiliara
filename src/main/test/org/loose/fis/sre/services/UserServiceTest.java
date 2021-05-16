package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    public static final String ADMIN = "admin";
    public static final String AGENT = "Agent";
    public static final String BUYER = "Buyer";

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

   @BeforeAll
    static void beforeAll() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        System.out.println("Before class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After class");
    }

    @Test
    @Order(1)

    @DisplayName("Database is initialized and there are no users")
    void testDataBaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
        System.out.println("1");
    }

    @Test
    @Order(2)

    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, ADMIN, ADMIN,ADMIN,ADMIN);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN, ADMIN));
        assertThat(user.getRole()).isEqualTo(ADMIN);
        System.out.println("2");
    }

    @Test
    @Order(3)

    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(ADMIN, ADMIN, ADMIN,ADMIN,ADMIN);
            UserService.addUser(ADMIN, ADMIN, ADMIN,ADMIN,ADMIN);
        });
        System.out.println("3");
    }

    @Test
    @Order(4)

    @DisplayName("User can login")
    void testUserCanLogin() throws UsernameAlreadyExistsException, IncorrectCredentials {
        UserService.addUser(AGENT, AGENT, AGENT,AGENT,AGENT);
        assertThat(UserService.CheckUserCredentials(AGENT,AGENT,AGENT)).isEqualTo(2);
        UserService.addUser(BUYER,BUYER,BUYER,BUYER,BUYER);
        assertThat(UserService.CheckUserCredentials(BUYER,BUYER,BUYER)).isEqualTo(1);
        System.out.println("4");
    }

    @Test
    @Order(5)

    @DisplayName("Username must be unique")
    void testUserDoesNotAlreadyExist() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(AGENT, AGENT, AGENT,AGENT,AGENT);
            UserService.checkUserDoesNotAlreadyExist(AGENT);
        });
        System.out.println("5");
    }

    @Test
    @Order(6)

    @DisplayName("User can not login with wrong credentials")
    void testUserCanNotLogin() {
        assertThrows(IncorrectCredentials.class, () -> {
            UserService.CheckUserCredentials(AGENT,BUYER,BUYER);
        });
        System.out.println("6");
    }

    @Test
    @Order(7)

    @DisplayName("Agent does exist")
    void testAgentDoesExist() {
        assertThrows(AgentDoesNotExistException.class, () -> {
            UserService.checkAgentDoesExist("agent");
        });
        System.out.println("7");
    }

    @Test
    @Order(8)

    @DisplayName("Username is correct")
    void testUsernameDoesExist() {
        assertThrows(IncorrectNameException.class, () -> {
            UserService.checkUsername("agent");
        });
        System.out.println("8");
    }

    @Test
    @Order(9)

    @DisplayName("Name is correct")
    void testNameDoesExist() {
        assertThrows(NoSchedulingsException.class, () -> {
            UserService.CheckNameCredentials("agent");
        });
        System.out.println("9");
    }

    @Test
    @Order(10)

    @DisplayName("Agent list is correct")
    void testAgentList() throws UsernameAlreadyExistsException {
        assertThat(UserService.agents_lsit()).isEqualTo("fullName='Agent', phoneNumber='Agent'\n");
    }
}