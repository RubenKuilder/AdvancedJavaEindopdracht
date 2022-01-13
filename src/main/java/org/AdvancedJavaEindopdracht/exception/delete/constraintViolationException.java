package org.AdvancedJavaEindopdracht.exception.delete;

public class constraintViolationException extends RuntimeException
{
    public constraintViolationException() {
        super("Not allowed to delete.");
    }
}
