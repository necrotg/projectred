package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tborder_item")
public class OrderItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItem_seq")
    @SequenceGenerator(name = "orderItem_seq", sequenceName = "orderItem_seq", allocationSize = 1)
    private Long orderItemId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "productId")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "orderId")
    @JsonIgnore
    @ToString.Exclude
    private Order order;
    private int quantity;
    private BigDecimal itemTotalPrice;

    public void updateTotals() {
        this.itemTotalPrice = product.getActualPrice().multiply(BigDecimal.valueOf(quantity));
    }

}