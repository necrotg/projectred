package model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    private Long cartId;
    private List<Product> products;
    private Float basePrice;
    private Float actualPrice;
}
