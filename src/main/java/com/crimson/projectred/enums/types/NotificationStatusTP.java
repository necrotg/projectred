package com.crimson.projectred.enums.types;

public enum NotificationStatusTP {
    ACTIVE("Active"),CANCELLED("Cancelled"),SENT("Sent"),FAILED("Failed");
    private final String value;
    NotificationStatusTP(String value) {
        this.value = value;
    }
}
