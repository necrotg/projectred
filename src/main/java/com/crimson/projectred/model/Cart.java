package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;
    private BigDecimal basePrice = BigDecimal.ZERO;
    private BigDecimal actualPrice = BigDecimal.ZERO;
    public void addItem(CartItem item) {
        this.cartItems.add(item);
        item.setCart(this);
        updateTotals();
    }

    public void removeItem(CartItem item) {
        this.cartItems.remove(item);
        item.setCart(null);
        updateTotals();
    }

    private void updateTotals() {
        this.actualPrice = cartItems.stream()
                .map(item -> item.getProduct().getActualPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.basePrice = cartItems.stream()
                .map(item -> item.getProduct().getBasePrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
