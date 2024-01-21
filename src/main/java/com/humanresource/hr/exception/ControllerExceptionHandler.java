package com.humanresource.hr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class ControllerExceptionHandler {

    public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    @ExceptionHandler(value = ControllerRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ControllerRequestException e){
        Map<String,String> errors = extractErrorsFromMessage(e.getMessage());

        ControllerException controllerException = new ControllerException(
                errors,
                BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")),
                false
        );

        return new ResponseEntity<>(controllerException, BAD_REQUEST);
    }

    private Map<String, String> extractErrorsFromMessage(String errorMessage) {
        Map<String, String> errors = new HashMap<>();

        String regex = "interpolatedMessage='([^']+)', propertyPath=([^,]+), rootBeanClass=([^,]+), messageTemplate='([^']+)'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(errorMessage);

        while (matcher.find()) {
            String message = matcher.group(1);
            String propertyPath = matcher.group(2);
            errors.put(propertyPath, message);
        }

        return errors;
    }
}
