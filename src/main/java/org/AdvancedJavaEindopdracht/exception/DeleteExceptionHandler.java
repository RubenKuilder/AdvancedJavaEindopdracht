package org.AdvancedJavaEindopdracht.exception;

import org.AdvancedJavaEindopdracht.exception.delete.NotDeletedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class DeleteExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotDeletedException.class)
    public ErrorMessage handleNotDeletedException(NotDeletedException exception, HttpServletRequest request) {
        String response = String.format(
                "Error %s - %s",
                exception.getMessage(),
                request.getRemoteAddr()
        );

        return new ErrorMessage(response);
    }
}