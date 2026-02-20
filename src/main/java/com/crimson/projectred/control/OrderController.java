package com.crimson.projectred.control;

import com.crimson.projectred.dto.OrderRequest;
import com.crimson.projectred.model.Order;
import com.crimson.projectred.model.StandardResponse;
import com.crimson.projectred.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
