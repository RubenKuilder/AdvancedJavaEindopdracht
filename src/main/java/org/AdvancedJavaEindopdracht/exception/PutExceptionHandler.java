package org.AdvancedJavaEindopdracht.exception;

import org.AdvancedJavaEindopdracht.exception.put.NoChangeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class PutExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoChangeException.class)
    public ErrorMessage handleAccessDeniedException(NoChangeException exception, HttpServletRequest request) {
        String response = String.format(
                "I have the message '%s' for %s",
                exception.getMessage(),
                request.getRemoteAddr()
        );

        return new ErrorMessage(response);
    }
}
