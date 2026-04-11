package com.crimson.projectred.control;

import com.crimson.projectred.dto.OrderRequestDTO;
import com.crimson.projectred.model.Order;
import com.crimson.projectred.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/{customerId}/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO, @PathVariable Long customerId) {
        orderService.validateInput(orderRequestDTO,customerId);
        Order order = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
