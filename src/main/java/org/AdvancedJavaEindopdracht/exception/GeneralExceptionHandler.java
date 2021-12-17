package org.AdvancedJavaEindopdracht.exception;

import org.AdvancedJavaEindopdracht.exception.general.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchFieldException.class)
    public ErrorMessage badBodyException(BadBodyException exception, HttpServletRequest request) {
        String response = String.format(
                "Error: ",
                exception.getMessage(),
                request.getRemoteAddr()
        );
        return new ErrorMessage(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ErrorMessage badMediaTypeException(BadMediaTypeException exception, HttpServletRequest request) {
        String response = String.format(
                "Error: ",
                exception.getMessage(),
                request.getRemoteAddr()
        );
        return new ErrorMessage(response);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorMessage dataNotFoundException(DataNotFoundException exception, HttpServletRequest request) {
        String response = String.format(
                "Error: ",
                exception.getMessage(),
                request.getRemoteAddr()
        );
        return new ErrorMessage(response);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorMessage generalGlobalException(GeneralGlobalException exception, HttpServletRequest request) {
        String response = String.format(
                "Error: ",
                exception.getMessage(),
                request.getRemoteAddr()
        );
        return new ErrorMessage(response);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(org.springframework.web.server.ResponseStatusException.class)
    public ErrorMessage responseStatusException(ResponseStatusException exception, HttpServletRequest request) {
        String response = String.format(
                "Error: ",
                "Status unknown, returning instead - " + exception.getMessage(),
                request.getRemoteAddr()
        );
        return new ErrorMessage(response);
    }
}
