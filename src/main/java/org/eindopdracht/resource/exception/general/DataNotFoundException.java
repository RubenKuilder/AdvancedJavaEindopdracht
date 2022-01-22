package org.eindopdracht.resource.exception.general;


public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super("Data not found - " + message);
    }
}
