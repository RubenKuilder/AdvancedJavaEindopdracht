package org.AdvancedJavaEindopdracht.exception.general;

import javax.persistence.EntityNotFoundException;

public class DataNotFoundException extends EntityNotFoundException {
    public DataNotFoundException() {
        super("Data not found.");
    }
}
