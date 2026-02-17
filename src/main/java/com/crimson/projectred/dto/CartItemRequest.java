package com.crimson.projectred.dto;

public record CartItemRequest(
        Long productId,
        Integer quantity
) {}
