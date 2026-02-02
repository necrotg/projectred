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
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;
    @JoinColumn(nullable = false,name = "shipping_address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address shippingAddress;
    @JoinColumn(nullable = false,name = "billing_address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address billingAddress;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "customer_id")
    private Customer customer;
    @Column(nullable = false)
    private String dimension;
    @Column(nullable = false)
    private Float baseTotalPrice;
    @Column(nullable = false)
    private Float actualTotalPrice;
}
