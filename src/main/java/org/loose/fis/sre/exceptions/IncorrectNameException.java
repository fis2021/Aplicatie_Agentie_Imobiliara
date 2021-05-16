package org.loose.fis.sre.exceptions;

public class IncorrectNameException extends Exception {

    private String Name;

    public IncorrectNameException(String Name) {
        super(String.format("The name %s is incorrect!", Name));
        this.Name = Name;
    }

    public String Name() {
        return Name;
    }
}
