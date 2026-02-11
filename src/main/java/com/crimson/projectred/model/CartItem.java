package com.crimson.projectred.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbcard_item")
public class CartItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartItem_seq")
    @SequenceGenerator(name = "cartItem_seq", sequenceName = "cartItem_SEQ", allocationSize = 1)
    private Long cartItemId;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "wishlistId")
    private Cart cart;
}
