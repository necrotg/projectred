import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    private Long id;
    private String name;
    private String phoneNumber;
    private String cpf;
    private Cards[] cards;
    private Address[] billingAddresses;
    private Address[] shippingAddresses;
    private Product[] cart;
    private Product[] wishlist;
    private Order[] orders;
}
