package com.crimson.projectred.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tborder")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    private Long orderId;
    @JoinColumn(nullable = false,name = "shipping_address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address shippingAddress;
    @JoinColumn(nullable = false,name = "billing_address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address billingAddress;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "customerId")
    private Customer customer;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<OrderItem> orderItems;
    @Column(nullable = false)
    private Float baseTotalPrice;
    @Column(nullable = false)
    private Float actualTotalPrice;
}
