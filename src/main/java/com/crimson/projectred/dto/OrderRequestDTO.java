package com.crimson.projectred.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderRequestDTO(
        @NotNull(message = "The order must contain items")
        @NotEmpty(message = "The order items list cannot be empty")
        @Valid
        List<ItemRequestDTO> orderItems,

        @NotNull(message = "Shipment option is required")
        @Positive(message = "Invalid shipment option ID")
        Long shipmentOptionId,

        @NotNull(message = "Payment card is required")
        @Positive(message = "Invalid card ID")
        Long cardId,

        @NotNull(message = "Shipment address is required")
        @Positive(message = "Invalid shipment address ID")
        Long shipmentAddressId,

        @NotNull(message = "Billing address is required")
        @Positive(message = "Invalid billing address ID")
        Long billingAddressId
) {}