package org.AdvancedJavaEindopdracht.exception.general;

public class GeneralGlobalException extends RuntimeException {
    public GeneralGlobalException() {
        super("Something went wrong when executing the request.");
    }
}