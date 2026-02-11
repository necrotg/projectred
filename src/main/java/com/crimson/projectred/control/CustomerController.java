package com.crimson.projectred.control;

import com.crimson.projectred.exception.handler.ResourceExceptionHandler;
import com.crimson.projectred.model.Cart;
import com.crimson.projectred.model.Wishlist;
import lombok.RequiredArgsConstructor;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.model.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crimson.projectred.service.CustomerService;

import java.util.Date;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        customer.setCart(new Cart());
        customer.setWishlist(new Wishlist());
        Customer savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email){
        Customer customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomerById(id);
    }
}
