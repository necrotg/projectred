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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CustomerService customerService;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductsRepository productsRepository;

    @Transactional
    public void addToCart(Long customerId, ItemRequestDTO cartItemRequestDTO) {
        Cart cart = cartRepository.findCartByCustomer_CustomerId(customerId)
                .orElseThrow(()->new BusinessException(ExceptionMessage.CUSTOMER_NOT_FOUND));
        Product product = productsRepository.findById(cartItemRequestDTO.productId()).orElseThrow(()->new NotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND));
        int quantity = cartItemRequestDTO.quantity();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cartItem.updateTotals();
        cart.addItem(cartItem);
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
}
