package com.crimson.projectred.dto.events;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record NotificationEventDTO(@NotNull Long notificationId, @NotNull String email, @NotNull String phoneNumber,
                                   @NotNull Map<String, Object> notificationData,@NotNull String template) {
}
