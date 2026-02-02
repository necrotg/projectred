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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nameSimplified;
    @Column(nullable = false)
    private String description;
    private String dimension;
    @Column(nullable = false)
    private String pathImages;
    private List<Review> reviews;
    @Column(nullable = false)
    private Float basePrice;
    @Column(nullable = false)
    private Float actualPrice;
    private int version;
}
