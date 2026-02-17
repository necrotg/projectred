package com.crimson.projectred.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbproducts")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nameSimplified;
    @Column(nullable = false)
    private String description;
    private String dimension;
    @Column(nullable = false)
    private String pathImages;
    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();
    @Column(nullable = false)
    private BigDecimal basePrice = BigDecimal.ZERO;
    @Column(nullable = false)
    private BigDecimal actualPrice =  BigDecimal.ZERO;
    private int version;
}
