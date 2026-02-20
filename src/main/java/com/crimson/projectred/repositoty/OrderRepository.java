package com.crimson.projectred.repositoty;

import com.crimson.projectred.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrdersByCustomer_CustomerId(Long customerCustomerId);
}
