package com.crimson.projectred.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardErrorResponse extends StandardResponse{
    private String error;

    public StandardErrorResponse(Long timestamp, int status, String message,String error) {
        super(timestamp, status, message);
        this.error = error;
    }
}