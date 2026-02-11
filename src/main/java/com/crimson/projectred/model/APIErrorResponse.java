package com.crimson.projectred.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIErrorResponse extends StandardErrorResponse{
    private String path;

    public APIErrorResponse(Long timestamp, int status, String error, String message, String path) {
        super(timestamp, status, error, message);
        this.path = path;
    }
}
