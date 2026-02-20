package com.crimson.projectred.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbpayment")
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long paymentId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @JoinColumn(name = "addressId")
    @OneToOne(fetch = FetchType.LAZY)
    private Address billingAddress;
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardId")
    private Card card;

}
