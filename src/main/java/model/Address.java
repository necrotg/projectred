package model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "tbaddresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    private String complement;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2) // Ex: "SP", "RJ"
    private String state;

    @Column(nullable = false, length = 9) // Ex: "01234-567"
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy evita carregar o cliente sem necessidade
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id != null && id.equals(address.id);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}