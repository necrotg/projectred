package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;
    @Column(nullable = false)
    private String promoCode;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Float amount;
    private int version;
}