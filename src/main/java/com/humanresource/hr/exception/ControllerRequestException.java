package com.humanresource.hr.exception;

public class ControllerRequestException extends RuntimeException {

    public ControllerRequestException(String message) {
        super(message);
    }

    public ControllerRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
