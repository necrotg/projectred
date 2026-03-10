package com.crimson.projectred.dto.events;

import java.util.Date;

public record NotificationStatusDTO(String status, Date date, String orderId, Long notificationId) {
}
