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
public class Wishlist {
    private long wishlistId;
    private List<Product> products;
}
