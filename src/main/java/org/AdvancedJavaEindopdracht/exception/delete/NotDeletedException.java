package org.AdvancedJavaEindopdracht.exception.delete;

public class NotDeletedException extends RuntimeException {
    public NotDeletedException() {
        super("No deletion occurred.");
    }
}
