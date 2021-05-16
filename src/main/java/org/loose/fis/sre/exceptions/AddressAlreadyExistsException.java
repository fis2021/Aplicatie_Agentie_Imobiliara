package org.loose.fis.sre.exceptions;

public class AddressAlreadyExistsException extends Exception {

    private String Address;

    public AddressAlreadyExistsException(String Address) {
        super(String.format("A house with the address %s already exists!", Address));
        this.Address = Address;

    }

    public String getAddress() {
        return Address;
    }
}
