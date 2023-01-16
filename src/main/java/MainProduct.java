import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MainProduct {
    @Id
    private Long id;
    private String name;
    private Product[] products;
}
