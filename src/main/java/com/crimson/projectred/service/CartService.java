package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.dto.ItemRequestDTO;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.exception.cust.NotFoundException;
import com.crimson.projectred.model.Cart;
import com.crimson.projectred.model.CartItem;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.model.Product;
import com.crimson.projectred.repository.CartItemRepository;
import com.crimson.projectred.repository.CartRepository;
import com.crimson.projectred.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    private final CustomerService customerService;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductsRepository productsRepository;
    private Product product;
    private Cart cart;

    @Transactional
    public void addToCart(Long customerId, ItemRequestDTO cartItemRequestDTO, String correlationId) {
        log.info("CartService::addToCart: adding items to cart for customer {} Correlation ID: {}", customerId, correlationId);
        validateInput(cartItemRequestDTO,customerId);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemRequestDTO.quantity());
        cartItem.setCart(cart);
        cartItem.updateTotals();
        cart.addItem(cartItem);
        log.info("CartService::addToCart: items added to cart for customer {} Correlation ID: {}", customerId, correlationId);
    }

    @Transactional
    public void removeFromCart(Long customerId, Long cartItemId) {
        Customer customer = customerService.getCustomerById(customerId);
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()-> new NotFoundException(ExceptionMessage.CART_ITEM_NOT_FOUND));
        customer.getCart().removeItem(cartItem);
    }

    public Cart getCartByCustomerId(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return customer.getCart();
    }

    public void validateInput(ItemRequestDTO cartItemRequestDTO,Long customerId){
        this.cart = cartRepository.findCartByCustomer_CustomerId(customerId)
                .orElseThrow(()->new BusinessException(ExceptionMessage.CUSTOMER_NOT_FOUND));
        productsRepository.findById(cartItemRequestDTO.productId())
                .orElseThrow(()->new NotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND));
    }
}
