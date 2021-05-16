package org.loose.fis.sre.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    //user
    public static String APPLICATION_FOLDER = ".agentie_imobiliara";

    public static  String HOUSE_FOLDER = ".house-database";

    public static String SCHEDULING_FOLDER = ".booking-database";
    //user
    private static final String USER_FOLDER = System.getProperty("user.home");

    public static final Path HOUSE_HOME_PATH = Paths.get(USER_FOLDER, HOUSE_FOLDER);
    //user
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    public static final Path BOOKING_HOME_PATH = Paths.get(USER_FOLDER, SCHEDULING_FOLDER);
    //user
    public static Path getPathToFile(String... path){ return getApplicationHomeFolder().resolve(Paths.get(".", path));
    }

    public static Path getPathToHouse(String... path)
    {
        return getHouseHomeFolder().resolve(Paths.get(".",path));
    }
    public static Path getPathToScheduling(String... path)
    {
        return getSchedulingHomeFolder().resolve(Paths.get(".",path));
    }

    //user
    public static Path getApplicationHomeFolder() {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    public static Path getSchedulingHomeFolder() {
        return Paths.get(USER_FOLDER, SCHEDULING_FOLDER);
    }
    public static Path getHouseHomeFolder() {
        return Paths.get(USER_FOLDER, HOUSE_FOLDER);
    }

    public static void initDirectory() {
        Path applicationHomePath = getApplicationHomeFolder();
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
    public static void initDirectoryScheduling()
    {
        Path bookingHomePath = getSchedulingHomeFolder();
        if(!Files.exists(bookingHomePath))
            bookingHomePath.toFile().mkdirs();
    }
    public static void initDirectory_house() {
        Path houseHomePath = getHouseHomeFolder();
        if (!Files.exists(houseHomePath))
            houseHomePath.toFile().mkdirs();
    }
}

