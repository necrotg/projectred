package com.crimson.projectred.model;

import com.crimson.projectred.dto.OrderRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "tbshipment_options")
public class ShipmentOption extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentOptionId;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private BigDecimal totalPrice;
    @Column(nullable = false)
    private int ETA;

}
