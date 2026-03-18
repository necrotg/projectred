package com.crimson.projectred.model;

import com.crimson.projectred.enums.types.NotificationStatusTP;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "tbnotification_events")
@EqualsAndHashCode(callSuper = true)
public class NotificationEvent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notif_seq")
    @SequenceGenerator(name = "notif_seq", sequenceName = "notif_seq", allocationSize = 1)
    private Long notificationId;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Order order;
    private String notificationTemplate;
    private int retries;
    @Enumerated(EnumType.STRING)
    private NotificationStatusTP notificationStatusTP;
}
