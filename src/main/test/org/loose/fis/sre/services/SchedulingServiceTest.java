package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.Scheduling;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SchedulingServiceTest {

    public static final String ADMIN = "admin";
    public static final String AGENT = "Agent";
    public static final String ADDRESS="Address";
    public static final String SIZE="Size";
    public static final String ROOMS="Rooms";

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @BeforeAll
    static void beforeAll() throws Exception {
        FileSystemService.SCHEDULING_FOLDER = ".test-booking";
        FileSystemService.initDirectoryScheduling();
        FileUtils.cleanDirectory(FileSystemService.getSchedulingHomeFolder().toFile());
        SchedulingService.initDatabase();
        FileSystemService.APPLICATION_FOLDER = ".test-registration-forbooking";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        System.out.println("Before class");
        FileSystemService.HOUSE_FOLDER = ".test-addhouses-forbooking";
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

    @DisplayName("Database is initialized and there are no bookings")
    void testDataBaseIsInitializedAndNoBookingIsPersisted() {
        assertThat(SchedulingService.getAllScheduling()).isNotNull();
        assertThat(SchedulingService.getAllScheduling()).isEmpty();
        System.out.println("1");
    }

    @Test
    @Order(2)

    @DisplayName("Scheduling can not be added for an agent that does not exist")
    void testBookingForWrongAgent() {
        assertThrows(AgentDoesNotExistException.class, () -> {
            SchedulingService.addScheduling(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN);
        });
        System.out.println("2");
    }

    @Test
    @Order(3)

    @DisplayName("Scheduling can not be added for a name that does not exist")
    void testBookingForWrongName() {
        assertThrows(IncorrectNameException.class, () -> {
            UserService.addUser(AGENT, AGENT, AGENT, AGENT, AGENT);
            SchedulingService.addScheduling(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, AGENT, ADMIN, ADMIN);
        });
        System.out.println("3");
    }

    @Test
    @Order(4)

    @DisplayName("Scheduling can not be added for an address that does not exist")
    void testBookingForWrongAddress() {
        assertThrows(HouseDoesNotExistsException.class, () -> {
            HouseService.addHouse(ADDRESS, SIZE, ROOMS);
            SchedulingService.addScheduling(ADMIN,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,AGENT);
        });
        System.out.println("4");
    }

    @Test
    @Order(5)

    @DisplayName("Scheduling can not be added for a date that does not exist")
    void testBookingForWrongDate() {
        assertThrows(IncorrectDateException.class, () -> {
            SchedulingService.addScheduling(ADDRESS,"31","February",ADMIN,ADMIN,AGENT,ADMIN,AGENT);
        });
        System.out.println("5");
    }

    @Test
    @Order(6)

    @DisplayName("Scheduling is added to database")
    void testBookingIsAddedToDatabase() throws IncorrectDateException, AgentDoesNotExistException, IncorrectNameException, SchedulingAlreadyExistsException, HouseDoesNotExistsException {
        SchedulingService.addScheduling(ADDRESS,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,AGENT);
        assertThat(SchedulingService.getAllScheduling()).isNotEmpty();
        assertThat(SchedulingService.getAllScheduling()).size().isEqualTo(1);
        Scheduling scheduling = SchedulingService.getAllScheduling().get(0);
        org.assertj.core.api.Assertions.assertThat(scheduling).isNotNull();
        org.assertj.core.api.Assertions.assertThat(scheduling.getAddress()).isEqualTo(ADDRESS);
        org.assertj.core.api.Assertions.assertThat(scheduling.getDay()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(scheduling.getMonth()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(scheduling.getYear()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(scheduling.getHour()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(scheduling.getAgentSchedule()).isEqualTo(AGENT);
        org.assertj.core.api.Assertions.assertThat(scheduling.getSpecial_req()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(scheduling.getUser()).isEqualTo(AGENT);
        System.out.println("6");
    }

    @Test
    @Order(7)

    @DisplayName("Scheduling can not be added twice")
    void testBookingCanNotBeAddedTwice() {
        assertThrows(SchedulingAlreadyExistsException.class, () -> {
            SchedulingService.addScheduling(ADMIN,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,ADMIN);
            SchedulingService.addScheduling(ADMIN,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,ADMIN);
        });
        System.out.println("7");
    }

    @Test
    @Order(8)

    @DisplayName("Scheduling list is corect")
    void testSeeBookings() throws NoSchedulingsException {
        assertThat(SchedulingService.seeSchedulings(AGENT)).isEqualTo("Scheduling{address= Address\n, day=admin, month=admin, year= admin, hour= admin\n, agent_book= Agent, special_req= admin\n, accept_booking= not responded, rejection_message=  , Name='Agent}\n");
        System.out.println("8");
    }

    @Test
    @Order(9)

    @DisplayName("Scheduling list is empty")
    void testSeeBookingsEmpty() {
        assertThrows(NoSchedulingsException.class, () -> {
                    SchedulingService.seeSchedulings("notAgent");
                }
        );
        System.out.println("9");
    }

    @Test
    @Order(10)

    @DisplayName("Scheduling does not already exist")
    void testBookingDesNotAlreadyExist() {
        assertThrows(SchedulingAlreadyExistsException.class, () -> {
            SchedulingService.addScheduling(ADMIN,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,ADMIN);
            SchedulingService.checkchedulingDoesNotAlreadyExist(ADMIN,ADMIN,ADMIN,ADMIN,AGENT);
                }
        );
        System.out.println("10");
    }
    
    @Test
    @Order(11)

    @DisplayName("Scheduling is approved")
    void testApproveBooking() throws IncorrectDateException, AgentDoesNotExistException, SchedulingNotFoundException {
        SchedulingService.approveScheduling(AGENT, ADMIN, ADMIN, ADMIN, ADMIN);
        Scheduling scheduling = SchedulingService.getAllScheduling().get(0);
        assertThat(scheduling.getAcceptScheduling()).isEqualTo("approved");
        System.out.println("11");

    }

    @Test
    @Order(12)

    @DisplayName("Scheduling date for approving is incorrect")
    void testApproveBookingNotDate() throws AgentDoesNotExistException, SchedulingNotFoundException {
        assertThrows(IncorrectDateException.class, () -> {
            SchedulingService.approveScheduling(AGENT, "20", "31", "February", "2020");
        });
        System.out.println("12");
    }

    @Test
    @Order(13)

    @DisplayName("Scheduling agent for approving is incorrect")
    void testApproveBookingNotAgent() throws IncorrectDateException, SchedulingNotFoundException {
        assertThrows(AgentDoesNotExistException.class, () -> {
            SchedulingService.approveScheduling("notAnAgent", "20", "27", "February", "2020");
        });
        System.out.println("13");
    }

    @Test
    @Order(14)

    @DisplayName("Scheduling for approving is not found")
    void testApproveBookingNotBooking() throws IncorrectDateException, AgentDoesNotExistException {
        assertThrows(SchedulingNotFoundException.class, () -> {
            SchedulingService.approveScheduling(AGENT, "20", "27", "February", "2020");
        });
        System.out.println("14");
    }

    @Test
    @Order(15)

    @DisplayName("Date is not correct ")
    void testCheckDate() {
        assertThrows(IncorrectDateException.class, () -> {
                    SchedulingService.addScheduling(ADDRESS,"31","June",ADMIN,ADMIN,AGENT,ADMIN,AGENT);
                }
        );
        System.out.println("15");
    }

    @Test
    @Order(16)

    @DisplayName("History of bookings is correct")
    void testSeeBookingHistory() throws NoSchedulingsException {
        assertThat(SchedulingService.seeHistorySchedulings(AGENT)).isEqualTo("Scheduling{address= Address\n, day=admin, month=admin, year= admin, hour= admin\n, agent_book= Agent, special_req= admin\n, accept_booking= approved, rejection_message= , Name='Agent}\n");
        System.out.println("16");

    }

    @Test
    @Order(17)

    @DisplayName("Scheduling rejected succefully")
    void testRejectBooking() throws IncorrectDateException, AgentDoesNotExistException, SchedulingNotFoundException
    {
        SchedulingService.rejectScheduling(AGENT, ADMIN, ADMIN, ADMIN, ADMIN, "onereason");
        Scheduling scheduling = SchedulingService.getAllScheduling().get(0);
        assertThat(scheduling.getAcceptScheduling()).isEqualTo("rejectet");
        System.out.println("17");
    }

    @Test
    @Order(18)

    @DisplayName("Scheduling date for rejecting is incorrect")
    void testRejectBookingNotDate() throws AgentDoesNotExistException, SchedulingNotFoundException {
        assertThrows(IncorrectDateException.class, () -> {
            SchedulingService.rejectScheduling(AGENT, "20", "31", "February", "2020", "onereason");
        });
        System.out.println("18");
    }

    @Test
    @Order(19)

    @DisplayName("Scheduling agent for rejecting is incorrect")
    void testRejectBookingNotAgent() throws IncorrectDateException, SchedulingNotFoundException {
        assertThrows(AgentDoesNotExistException.class, () -> {
            SchedulingService.rejectScheduling("notAnAgent", "20", "27", "February", "2020", "thereason");
        });
        System.out.println("19");
    }

    @Test
    @Order(20)

    @DisplayName("Scheduling for rejecting is not found")
    void testRejectBookingNotBooking() throws IncorrectDateException, AgentDoesNotExistException {
        assertThrows(SchedulingNotFoundException.class, () -> {
            SchedulingService.rejectScheduling(AGENT, "20", "27", "February", "2020", "one reason");
        });
        System.out.println("20");
    }
}

