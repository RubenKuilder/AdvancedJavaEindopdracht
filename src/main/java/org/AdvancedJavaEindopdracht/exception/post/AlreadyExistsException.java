package org.AdvancedJavaEindopdracht.exception.post;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException() {
        super("This data already exists.");
    }
}