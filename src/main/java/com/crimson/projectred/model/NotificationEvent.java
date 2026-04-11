package com.crimson.projectred.model;

import com.crimson.projectred.enums.types.NotificationStatusTP;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "tbnotification_events")
public class NotificationEvent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notif_seq")
    @SequenceGenerator(name = "notif_seq", sequenceName = "notif_seq", allocationSize = 1)
    private Long notificationId;
    @ManyToOne
    @JoinColumn(nullable = false,name = "customerId")
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "orderId")
    @JsonIgnore
    @ToString.Exclude
    private Order order;
    private String notificationTemplate;
    private String eventData;
    private int retries;
    @Enumerated(EnumType.STRING)
    private NotificationStatusTP notificationStatusTP;
}
