package com.humanresource.hr.helper;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class DeleteResponse {
    private String message;
    private boolean success;

    public DeleteResponse(String msg, Boolean success) {
        this.message = msg;
        this.success = success;
    }

}
