package com.humanresource.hr.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ControllerException {
    private Map<String,String> errors;
    private  HttpStatus httpStatus;
    private  ZonedDateTime timestamp;
    private Boolean success;

    public ControllerException(Map<String,String> message, HttpStatus badRequest, ZonedDateTime z, Boolean success) {
        this.errors = message;
        this.httpStatus = badRequest;
        this.timestamp = z;
        this.success = success;
    }
}
