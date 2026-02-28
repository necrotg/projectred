package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.dto.OrderRequest;
import com.crimson.projectred.enums.types.OrderStateTP;
import com.crimson.projectred.enums.types.OrderStatusTP;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.model.*;
import com.crimson.projectred.repositoty.AddressRepository;
import com.crimson.projectred.repositoty.CustomerRepository;
import com.crimson.projectred.repositoty.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AddressService addressService;
    private final CustomerService customerService;
    private final CardService cardService;
    private final ShipmentOptionsService shipmentOptionService;
    private final ProductsService productsService;

    @Transactional
    public Order createOrder(OrderRequest orderRequest,Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        Address billingAddress = addressService.getAddressById(orderRequest.billingAddressId());
        Card card = cardService.findByCardId(orderRequest.cardId());
        Payment payment = new Payment();
        payment.setCard(card);
        payment.setBillingAddress(billingAddress);
        payment.setCustomer(customer);
        Address shipmentAddress = addressService.getAddressById(orderRequest.shipmentAddressId());
        ShipmentOption shipmentOption = shipmentOptionService.getShipmentOptionById(orderRequest.shipmentOptionId());
        Shipping shipping = new Shipping();
        shipping.setAddress(shipmentAddress);
        shipping.setCustomer(customer);
        shipping.setShipmentOption(shipmentOption);
        List<OrderItem> orderItems = new ArrayList<>();

        orderRequest.orderItems().forEach(orderItemFromRequest->{
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(orderItemFromRequest.quantity());
            Product product = productsService.getProductById(orderItemFromRequest.productId());
            orderItem.setProduct(product);
            orderItem.updateTotals();
            orderItems.add(orderItem);
        });

        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setCustomer(customer);
        order.setShipping(shipping);
        order.setPayment(payment);
        order.setState(OrderStateTP.ACTIVE);
        order.setStatus(OrderStatusTP.CREATED);
        order.updateTotals(shipping.getShipmentOption().getTotalPrice());

        orderItems.forEach(orderItem -> {
            orderItem.setOrder(order);
        });
        customer.getOrders().add(order);

        return order;
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
       return orderRepository.getOrdersByCustomer_CustomerId(customerId);
    }
}
