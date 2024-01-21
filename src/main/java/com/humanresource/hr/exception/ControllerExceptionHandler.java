package com.humanresource.hr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    @ExceptionHandler(value = ControllerRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ControllerRequestException e){

        ControllerException controllerException = new ControllerException(
                e.getMessage(),
                BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")),
                false
        );

        return new ResponseEntity<>(controllerException, BAD_REQUEST);
    }
}
