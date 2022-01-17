package org.AdvancedJavaEindopdracht.exception.delete;

import java.sql.SQLIntegrityConstraintViolationException;

public class sqlIntegrityConstraintViolationException extends SQLIntegrityConstraintViolationException
{
    public sqlIntegrityConstraintViolationException() {
        super("Not allowed to delete.");
    }
}
