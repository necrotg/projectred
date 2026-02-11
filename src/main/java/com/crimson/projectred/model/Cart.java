package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbcart")
public class Cart extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_SEQ", allocationSize = 1)
    private Long cartId;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private Customer customer;
    private Float basePrice;
    private Float actualPrice;

}
