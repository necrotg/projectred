package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.dto.OrderRequestDTO;
import com.crimson.projectred.exception.cust.InvalidInputException;
import com.crimson.projectred.factory.OrderFactory;
import com.crimson.projectred.mappers.OrderItemsMapper;
import com.crimson.projectred.model.*;
import com.crimson.projectred.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemsMapper orderItemsMapper;
    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final CustomerService customerService;
    private final CardService cardService;
    private final ShipmentOptionsService shipmentOptionService;
    private final NotificationService notificationService;
    private Customer customer;
    private Address billingAddress;
    private Card card;
    private Address shipmentAddress;
    private ShipmentOption shipmentOption;

    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItemsMapper.mapOrderItems(orderRequestDTO, orderItems);
        Order order = OrderFactory.createOrder(customer,billingAddress,shipmentAddress,shipmentOption,card,orderItems);
        orderRepository.save(order);
        sendOrderConfirmationNotification(order);
        return order;
    }

    public void sendOrderConfirmationNotification(Order order){
        notificationService.sendConfirmationNotification(order);
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
       return orderRepository.getOrdersByCustomer_CustomerId(customerId);
    }

    public void validateInput(@Valid OrderRequestDTO orderRequestDTO, Long customerId) {
        this.customer = customerService.getCustomerById(customerId);
        this.billingAddress = addressService.getAddressById(orderRequestDTO.billingAddressId());
        if(!customer.equals(billingAddress.getCustomer())){
            throw new InvalidInputException(ExceptionMessage.INVALID_ADDRESS);
        }
        this.shipmentAddress = addressService.getAddressById(orderRequestDTO.shipmentAddressId());
        if(!customer.equals(shipmentAddress.getCustomer())){
            throw new InvalidInputException(ExceptionMessage.INVALID_ADDRESS);
        }
        this.card = cardService.findByCardId(orderRequestDTO.cardId());
        if(!customer.equals(card.getCustomer())){
            throw new InvalidInputException(ExceptionMessage.INVALID_CARD);
        }
        this.shipmentOption = shipmentOptionService.getShipmentOptionById(orderRequestDTO.shipmentOptionId());
    }
}
