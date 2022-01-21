package org.eindopdracht.resource.exception.general;

public class NoContentException extends RuntimeException {
    public NoContentException(String message) {
        super("No Content - " + message);
    }
}
