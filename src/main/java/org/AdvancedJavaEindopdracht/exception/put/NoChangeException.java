package org.AdvancedJavaEindopdracht.exception.put;

public class NoChangeException extends RuntimeException {
    public NoChangeException(String message) {
        super("No changes occurred");
    }
}
