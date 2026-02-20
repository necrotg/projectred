package com.crimson.projectred.enums.types;

import lombok.Getter;

@Getter
public enum OrderStatusTP {
    CREATED("Created"),SUBMITTED("Submitted");
    private final String value;
    OrderStatusTP(String value) {
        this.value = value;
    }
}
