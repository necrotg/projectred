package com.crimson.projectred.repository;

import com.crimson.projectred.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByNameIgnoreCase(@NonNull String name);
}
