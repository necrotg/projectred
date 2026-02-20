package com.crimson.projectred.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbshipping_details")
public class Shipping extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(mappedBy = "shipping")
    private Order order;
    @OneToOne(mappedBy = "shipping")
    private ShipmentOption shipmentOption;
}
