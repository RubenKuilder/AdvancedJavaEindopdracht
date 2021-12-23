package org.AdvancedJavaEindopdracht.exception.get;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("Access denied");
    }
}
