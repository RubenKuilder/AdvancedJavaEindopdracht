package org.AdvancedJavaEindopdracht.exception.delete;

import org.springframework.dao.DataIntegrityViolationException;

public class dataIntegrityViolationException extends DataIntegrityViolationException
{
    public dataIntegrityViolationException() {
        super("Not allowed to delete.");
    }
}
