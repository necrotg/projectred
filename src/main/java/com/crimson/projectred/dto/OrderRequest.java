package com.crimson.projectred.dto;

import com.crimson.projectred.model.Card;
import com.crimson.projectred.model.OrderItem;
import com.crimson.projectred.model.ShipmentOption;

import java.io.Serializable;
import java.util.List;

public record OrderRequest(Long customerId, List<OrderItem> orderItems, Long shipmentOptionId, Long cardId, Long shipmentAddressId, Long billingAddressId) {
}
