package com.crimson.projectred.factory;

import com.crimson.projectred.enums.types.OrderStateTP;
import com.crimson.projectred.enums.types.OrderStatusTP;
import com.crimson.projectred.model.*;

import java.util.List;

public class OrderFactory {
    public static Order createOrder(Customer customer, Address billingAddress, Address shipmentAddress, ShipmentOption shipmentOption, Card card, List<OrderItem> orderItems){
        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setCustomer(customer);
        order.setShipping(new Shipping(shipmentAddress,customer,shipmentOption));
        order.setPayment(new Payment(card,billingAddress,customer));
        order.setState(OrderStateTP.ACTIVE);
        order.setStatus(OrderStatusTP.CREATED);
        order.updateTotals(shipmentOption.getTotalPrice());
        orderItems.forEach(orderItem -> orderItem.setOrder(order));
        customer.getOrders().add(order);
        return order;
    }
}
