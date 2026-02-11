package com.crimson.projectred.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tborder_item")
public class OrderItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItem_seq")
    @SequenceGenerator(name = "orderItem_seq", sequenceName = "orderItem_seq", allocationSize = 1)
    private Long orderItemId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "productId")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "orderId")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "customerId")
    private Customer customer;

}
