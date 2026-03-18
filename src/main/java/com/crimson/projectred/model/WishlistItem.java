package com.crimson.projectred.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbwishlist_items")
public class WishlistItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistItemId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "wishlistId")
    private Wishlist wishlist;
    @ManyToOne
    @JoinColumn(nullable = false,name = "productId")
    private Product product;
}
