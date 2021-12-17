package org.AdvancedJavaEindopdracht.exception.post;

public class NotStoredException extends RuntimeException{
    public NotStoredException() {
        super("The data could not be stored.");
    }
}
