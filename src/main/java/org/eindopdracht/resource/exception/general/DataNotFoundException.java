package org.eindopdracht.resource.exception.general;


public class DataNotFoundException extends RuntimeException  {
    public DataNotFoundException() {
        super("Data not found.");
    }
}
