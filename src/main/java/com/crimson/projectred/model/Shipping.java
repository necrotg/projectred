package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbshipping_details")
public class Shipping extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    @OneToOne
    private ShipmentOption shipmentOption;
}
