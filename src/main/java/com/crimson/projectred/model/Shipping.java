package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "tbshipping_details")
@NoArgsConstructor
public class Shipping extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shippingTrackingLink;
    private String shippingStatus;
    private int daysForArrival;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;
    @OneToOne
    @JsonIgnore
    @ToString.Exclude
    private Order order;
    @ManyToOne
    private ShipmentOption shipmentOption;

    public Shipping(Address shipmentAddress, Customer customer, ShipmentOption shipmentOption) {
        this.address = shipmentAddress;
        this.customer = customer;
        this.shipmentOption = shipmentOption;
    }
}
