package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class House {
    @Id
    private String Address;
    private String Size;
    private String Rooms;

    public House(String address, String size, String rooms) {
        Address = address;
        Size = size;
        Rooms = rooms;
    }

    public House() {
    }

    public String getAddress() {
        return Address;
    }

    public String getSize() {
        return Size;
    }

    public String getRooms() {
        return Rooms;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setSize(String size) {
        Size = size;
    }

    public void setRooms(String rooms) {
        Rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(Address, house.Address) && Objects.equals(Size, house.Size) && Objects.equals(Rooms, house.Rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Address, Size, Rooms);
    }

    @Override
    public String toString() {
        return
                "Address=" + Address  +
                ", Size= " + Size +
                ", Rooms= " + Rooms;
    }
}
