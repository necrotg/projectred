import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    private Long id;
    private String name;
    private String nameSimplified;
    private String description;
    private String dimension;
    private String pathImages;
    private AvaliationModel[] avaliations;
    private Float basePrice;
    private Float actualPrice;
    private Shipping[] shippings;


}
