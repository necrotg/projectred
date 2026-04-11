package com.crimson.projectred.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbreview")
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @Column(nullable = false)
    private String grade;
    @Column(nullable = false)
    private String text;
}
