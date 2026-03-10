package com.crimson.projectred.dto.events;

public record NotificationEventDTO(Long notificationId, String email, String phoneNumber,
                                   java.util.Map<String, Object> notificationData, String template) {
}
