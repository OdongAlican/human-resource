package com.humanresource.hr.user;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidationHandler {

    public Map<String,Object> ResponseEntityExceptionHandler(Exception e) {
        Map<String,Object> errors = new HashMap<String,Object>();

        if(e instanceof RuntimeException runtimeException){
            String[] errorDetails = runtimeException.getMessage().split("/n");
            errors.put("errors", errorDetails);
            System.out.println(errors);
        }

        return errors;
    }
}
