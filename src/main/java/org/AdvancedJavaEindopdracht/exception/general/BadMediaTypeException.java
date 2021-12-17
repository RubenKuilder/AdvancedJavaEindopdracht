package org.AdvancedJavaEindopdracht.exception.general;

import org.springframework.web.HttpMediaTypeNotAcceptableException;

public class BadMediaTypeException extends HttpMediaTypeNotAcceptableException {
    public BadMediaTypeException() {
        super("The given mediatype is not valid, use JSON instead.");
    }
}
