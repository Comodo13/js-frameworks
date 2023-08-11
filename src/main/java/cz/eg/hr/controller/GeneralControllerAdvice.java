package cz.eg.hr.controller;

import cz.eg.hr.rest.exception.CustomExceptionBody;
import cz.eg.hr.rest.exception.RequestFormatException;
import cz.eg.hr.rest.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler(value = RequestFormatException.class)
    public ResponseEntity<Object> handleRequestFormatException(RequestFormatException e) {
        CustomExceptionBody customExceptionBody = new CustomExceptionBody(e.getMessage(), ZonedDateTime.now());

        return new ResponseEntity<>(customExceptionBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        CustomExceptionBody customExceptionBody = new CustomExceptionBody(e.getMessage(), ZonedDateTime.now());

        return new ResponseEntity<>(customExceptionBody, HttpStatus.NOT_FOUND);
    }
}
