package com.crimson.projectred.factory;

import com.crimson.projectred.dto.CustomerDTO;
import com.crimson.projectred.model.Address;
import com.crimson.projectred.model.Cart;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.model.Wishlist;

import java.util.ArrayList;

public class CustomerFactory {
    public static Customer createCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.firstName());
        customer.setSurName(customerDTO.surName());
        customer.setPhoneNumber(customerDTO.phoneNumber());
        customer.setEmail(customerDTO.email());
        customer.setCpf(customerDTO.cpf());
        customer.setUserName(customerDTO.userName());
        customer.setCart(new Cart());
        customer.setWishlist(new Wishlist());
        customer.setAddresses(new ArrayList<>());
        customer.setOrders(new ArrayList<>());
        customer.setCards(new ArrayList<>());
        customer.setReviews(new ArrayList<>());
        return customer;
    }
}
