package com.crimson.projectred.repositoty;

import com.crimson.projectred.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product,Long> {
}
