package org.eindopdracht.resource.exception.general;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("A bad request has been send. Check your body & headers.");
    }
}
