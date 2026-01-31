package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(nullable = false)
    private List<Product> products;
    @Column(nullable = false)
    private Address shippingAddress;
    @Column(nullable = false)
    private Address billingAddress;
    @Column(nullable = false)
    private String dimension;
    @Column(nullable = false)
    private Float baseTotalPrice;
    @Column(nullable = false)
    private Float actualTotalPrice;
}
