package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbpayment")
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq", allocationSize = 1)
    private Long PaymentID;
    @OneToOne
    private Address billingAddress;
    @JoinColumn(name = "cardId")
    @OneToOne
    private Card card;
    @JoinColumn(name = "customerId")
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "orderId")
    @JsonIgnore
    @ToString.Exclude
    private Order order;
}
