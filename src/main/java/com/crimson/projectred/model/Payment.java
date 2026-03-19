package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbpayment")
@EqualsAndHashCode(callSuper = true)
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq", allocationSize = 1)
    private Long paymentId;
    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address billingAddress;
    @JoinColumn(name = "cardId")
    @ManyToOne
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
