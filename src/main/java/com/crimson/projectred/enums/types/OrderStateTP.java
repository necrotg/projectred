package com.crimson.projectred.enums.types;

import lombok.Getter;

@Getter
public enum OrderStateTP {
    ACTIVE("Active"),CANCELLED("Cancelled");
    private final String value;
    OrderStateTP(String value) {
        this.value = value;
    }

}
