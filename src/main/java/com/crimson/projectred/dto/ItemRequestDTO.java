package com.crimson.projectred.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemRequestDTO(
        @NotNull(message = "Product ID must be present")
        Long productId,
        @NotNull(message = "Quantity of products must be present")
        @Min(value = 1, message = "Minimum of product per order is 1")
        @Max(value = 10, message = "Maximum of product per order is 1")
        Integer quantity
) {}
