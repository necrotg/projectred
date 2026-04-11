package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "tbpayment")
@NoArgsConstructor
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq", allocationSize = 1)
    private Long paymentId;
    @ManyToOne
    @JoinColumn(name = "addressId",nullable = false)
    private Address billingAddress;
    @JoinColumn(name = "cardId",nullable = false)
    @ManyToOne
    private Card card;
    @JoinColumn(name = "customerId",nullable = false)
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "orderId",nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Order order;

    public Payment(@NotNull Card card, @NotNull Address billingAddress, @NotNull Customer customer) {
        this.card = card;
        this.billingAddress = billingAddress;
        this.customer = customer;
    }

}
