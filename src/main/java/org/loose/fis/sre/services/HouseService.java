package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.AddressAlreadyExistsException;
import org.loose.fis.sre.exceptions.HouseDoesNotExistsException;
import org.loose.fis.sre.model.House;

import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToHouse;

public class HouseService {

    private static ObjectRepository<House> houseRepository;

    public static void initDatabase() {
        FileSystemService.initDirectory_house();
        Nitrite database = Nitrite.builder()
                .filePath(getPathToHouse("house-database.db").toFile())
                .openOrCreate();
        houseRepository = database.getRepository(House.class);
    }
    public static ObjectRepository<House> getHouseRepository()
    {
        return houseRepository;
    }
    public static String  seeHouses()
    {
        String s="";
        for (House house : houseRepository.find())
        {

                s = s + house.toString();
                s = s + "\n";
        }
        return s;
    }

    public static void addHouse(String Address,String Size,String Rooms) throws AddressAlreadyExistsException {

        checkAddressDoesNotAlreadyExist(Address);
        houseRepository.insert(new House(Address,Size,Rooms));
    }
    public static void editHouse(String Address,String Size,String Rooms) throws HouseDoesNotExistsException
    {
        for (House house : houseRepository.find())
        {
            if(Objects.equals(Address, house.getAddress())) {
                //house.setAddress(Address);
                house.setSize(Size);
                house.setRooms(Rooms);
                houseRepository.update(house);
                return;
            }
        }
        throw new HouseDoesNotExistsException(Address);
    }
    public static void deleteHouse(String Address) throws HouseDoesNotExistsException
    {
        for (House house : houseRepository.find())
        {
            if(Objects.equals(Address, house.getAddress())) {

                houseRepository.remove(house);
                return;
            }
        }
        throw new HouseDoesNotExistsException(Address);
    }
    public static String searchHouse(String address) throws HouseDoesNotExistsException
    {

        for (House house : houseRepository.find())
        {
            if(Objects.equals(address, house.getAddress())) {
                return house.toString();
            }
        }
        throw new HouseDoesNotExistsException(address);
    }
    public static void checkAddressDoesNotAlreadyExist(String address) throws AddressAlreadyExistsException {
        for (House house : houseRepository.find()) {
            if (Objects.equals(address, house.getAddress()))
                throw new AddressAlreadyExistsException(address);
        }
    }
    public static void checkAddressDoesExist(String address) throws HouseDoesNotExistsException {
        for (House house : houseRepository.find()) {
            if (Objects.equals(address, house.getAddress()))
                return;
        }
        throw new HouseDoesNotExistsException(address);
    }
    public static List<House> getAllHouses() {
        return houseRepository.find().toList();
    }
}