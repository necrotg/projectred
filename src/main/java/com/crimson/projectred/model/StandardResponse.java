package com.crimson.projectred.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StandardResponse {
    private Long timestamp;
    private int status;
    private String message;
}
