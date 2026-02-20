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

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private AddressService addressService;
    private CustomerService customerService;
    private CardService cardService;
    private ShipmentOptionsService shipmentOptionService;

    @Transactional
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        Customer customer = customerService.getCustomerById(orderRequest.customerId());
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
        Order order = new Order();
        order.setOrderItems(orderRequest.orderItems());
        order.setCustomer(customer);
        order.setShipping(shipping);
        order.setPayment(payment);
        order.setState(OrderStateTP.ACTIVE.getValue());
        order.setStatus(OrderStatusTP.CREATED.getValue());
        order.updateTotals();
        return order;
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
       return orderRepository.getOrdersByCustomer_CustomerId(customerId);
    }
}
