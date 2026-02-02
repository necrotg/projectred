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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @OneToMany(mappedBy = "product")
    private List<Product> products;
    @ManyToOne(fetch = FetchType.LAZY) // Lazy evita carregar o cliente sem necessidade
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private Float basePrice;
    private Float actualPrice;
}
