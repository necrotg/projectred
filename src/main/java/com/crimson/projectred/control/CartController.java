package com.crimson.projectred.control;

import com.crimson.projectred.constant.ResponseMessage;
import com.crimson.projectred.dto.CartItemRequest;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.model.*;
import com.crimson.projectred.service.CartService;
import com.crimson.projectred.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/{customerId}/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<StandardResponse> addToCart(@PathVariable Long customerId, @RequestBody CartItemRequest cartItemRequest) {
        cartService.addToCart(customerId, cartItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardResponse(HttpStatus.CREATED.value(), ResponseMessage.PRODUCT_ADDED));
    }
    @GetMapping
    public ResponseEntity<Cart> getCart(@PathVariable Long customerId) {
        Cart cart = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cart);
    }
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<StandardResponse> removeFromCartItem(@PathVariable Long customerId,@PathVariable Long cartItemId) {
        cartService.removeFromCart(customerId,cartItemId);
        return ResponseEntity.status(HttpStatus.OK).body(new StandardResponse(HttpStatus.OK.value(),ResponseMessage.CART_ITEM_REMOVED));
    }
}
