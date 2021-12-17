package org.AdvancedJavaEindopdracht.exception.general;

import org.springframework.http.HttpStatus;

public class ResponseStatusException extends org.springframework.web.server.ResponseStatusException {
    public ResponseStatusException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
