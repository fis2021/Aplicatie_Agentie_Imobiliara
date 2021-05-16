package org.loose.fis.sre.exceptions;

public class NoSchedulingsException extends Exception {

    private String Name;

    public NoSchedulingsException(String Name) {
        super(String.format("No bookings for  %s!", Name));
        this.Name = Name;
    }

    public String Name() {
        return Name;
    }
}

