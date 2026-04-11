package com.crimson.projectred.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "Product name is required")
        @Size(max = 255)
        String name,
        @NotBlank(message = "Simplified name is required")
        String nameSimplified,
        @NotBlank(message = "Description is required")
        String description,
        String dimension,
        @NotBlank(message = "Image path is required")
        String pathImages,
        @NotNull(message = "Base price is required")
        @PositiveOrZero(message = "Base price cannot be negative")
        BigDecimal basePrice,
        @NotNull(message = "Actual price is required")
        @PositiveOrZero(message = "Actual price cannot be negative")
        BigDecimal actualPrice
) {}
