package com.crimson.projectred.dto;

import java.util.List;

public record OrderRequest(List<ItemRequest> orderItems, Long shipmentOptionId, Long cardId, Long shipmentAddressId, Long billingAddressId) {
}
