package org.AdvancedJavaEindopdracht.resource.exception;

import org.AdvancedJavaEindopdracht.resource.exception.general.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorMessage badRequestException(BadRequestException exception, HttpServletRequest request)  {
        return new ErrorMessage(String.format("message: ", exception.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public ErrorMessage dataNotFoundException(DataNotFoundException exception, HttpServletRequest request) {
        return new ErrorMessage("message: " + exception.getMessage());
    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(RuntimeException.class)
//    public ErrorMessage generalException(RuntimeException exception) {
//        return new ErrorMessage("Something went wrong when executing the request.");
//    }

}
