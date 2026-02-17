package com.crimson.projectred.repositoty;

import com.crimson.projectred.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
