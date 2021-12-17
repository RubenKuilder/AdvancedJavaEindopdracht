package org.AdvancedJavaEindopdracht.exception.general;

public class BadBodyException extends NoSuchFieldException {
    public BadBodyException() {
        super("The body that has been give is bad.");
    }
}
