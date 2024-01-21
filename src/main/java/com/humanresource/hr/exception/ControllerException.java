package com.humanresource.hr.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class ControllerException {
    private  String message;
    private  HttpStatus httpStatus;
    private  ZonedDateTime timestamp;
    private Boolean success;

    public ControllerException(String message, HttpStatus badRequest, ZonedDateTime z, Boolean success) {
        this.message = message;
        this.httpStatus = badRequest;
        this.timestamp = z;
        this.success = success;
    }
}
