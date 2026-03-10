package com.crimson.projectred.repositoty;

import com.crimson.projectred.model.events.NotificationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationEventRepository extends JpaRepository<NotificationEvent, Long> {
}
