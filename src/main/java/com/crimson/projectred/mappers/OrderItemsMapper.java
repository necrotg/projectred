package com.crimson.projectred.mappers;

import com.crimson.projectred.dto.OrderRequestDTO;
import com.crimson.projectred.model.OrderItem;
import com.crimson.projectred.model.Product;
import com.crimson.projectred.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderItemsMapper {

    private final ProductsService productsService;

    public void mapOrderItems(OrderRequestDTO orderRequestDTO, List<OrderItem> orderItems){
        orderRequestDTO.orderItems().forEach(orderItemFromRequest->{
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(orderItemFromRequest.quantity());
            Product product = productsService.getProductById(orderItemFromRequest.productId());
            orderItem.setProduct(product);
            orderItem.updateTotals();
            orderItems.add(orderItem);
        });
    }
}
