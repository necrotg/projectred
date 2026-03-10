package com.crimson.projectred.model.events;

import com.crimson.projectred.constant.NotificationTemplate;
import com.crimson.projectred.enums.types.NotificationStatusTP;
import com.crimson.projectred.model.BaseEntity;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.model.Order;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbnotification_events")
public class NotificationEvent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long notificationId;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Order order;
    private String notificationTemplate;
    private int retries;
    private NotificationStatusTP notificationStatusTP;
}
