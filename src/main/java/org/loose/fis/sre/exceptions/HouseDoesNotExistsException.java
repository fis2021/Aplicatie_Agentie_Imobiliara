package org.loose.fis.sre.exceptions;

public class HouseDoesNotExistsException extends Exception {

    private String Address;

    public HouseDoesNotExistsException(String Address) {
        super(String.format("A house with the address %s does not exist!", Address));
        this.Address = Address;

    }

    public String getAddress() {
        return Address;
    }
}
