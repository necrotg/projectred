package com.crimson.projectred.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardResponse {
    private Long timestamp;
    private int status;
    private String message;

    public StandardResponse(int status, String message) {
        this.timestamp = System.currentTimeMillis();
        this.status = status;
        this.message = message;
    }
}
