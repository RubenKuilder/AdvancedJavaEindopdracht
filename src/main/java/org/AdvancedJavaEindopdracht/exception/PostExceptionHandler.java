package org.AdvancedJavaEindopdracht.exception;

import org.AdvancedJavaEindopdracht.exception.post.AlreadyExistsException;
import org.AdvancedJavaEindopdracht.exception.post.NotStoredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class PostExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotStoredException.class)
    public ErrorMessage handleNotStoredException(NotStoredException exception, HttpServletRequest request) {
        String response = String.format(
                "Error: %s - %s",
                exception.getMessage(),
                request.getRemoteAddr()
        );

        return new ErrorMessage(response);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsException.class)
    public ErrorMessage handleAlreadyExistsException(AlreadyExistsException exception, HttpServletRequest request) {
        String response = String.format(
                "Error: %s - %s",
                exception.getMessage(),
                request.getRemoteAddr()
        );

        return new ErrorMessage(response);
    }
}
