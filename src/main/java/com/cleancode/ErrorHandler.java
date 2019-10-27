package com.cleancode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(FlightNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleFlightNotFoundException(FlightNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidInputException(InvalidInputException exception) {
        return exception.getMessage();
    }
}
