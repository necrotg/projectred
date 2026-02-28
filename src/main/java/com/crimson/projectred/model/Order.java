package com.crimson.projectred.model;

import com.crimson.projectred.enums.types.OrderStateTP;
import com.crimson.projectred.enums.types.OrderStatusTP;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tborder")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    private Long orderId;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "order")
    private Payment payment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "customerId")
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<OrderItem> orderItems;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Shipping shipping;
    @Column(nullable = false)
    private OrderStatusTP status;
    @Column(nullable = false)
    private OrderStateTP state;
    @Column(nullable = false)
    private BigDecimal baseTotalPrice;
    @Column(nullable = false)
    private BigDecimal actualTotalPrice;
    @Column(nullable = false)
    private BigDecimal totalToBePaid;

    public void updateTotals(BigDecimal shipmentFee) {
        this.actualTotalPrice = orderItems.stream()
                .map(item -> item.getProduct().getActualPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.totalToBePaid = this.actualTotalPrice.add(shipmentFee);

        this.baseTotalPrice = orderItems.stream()
                .map(item -> item.getProduct().getBasePrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
