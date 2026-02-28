package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbcart_item")
public class CartItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartItem_seq")
    @SequenceGenerator(name = "cartItem_seq", sequenceName = "cartItem_SEQ", allocationSize = 1)
    private Long cartItemId;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    private BigDecimal itemTotalPrice;
    private int quantity;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "cartId")
    private Cart cart;

    public void updateTotals() {
        this.itemTotalPrice = product.getActualPrice().multiply(BigDecimal.valueOf(quantity));
    }

}
