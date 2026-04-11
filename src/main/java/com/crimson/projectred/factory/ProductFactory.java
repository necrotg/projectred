package com.crimson.projectred.factory;

import com.crimson.projectred.dto.ProductRequestDTO;
import com.crimson.projectred.model.Product;

public class ProductFactory {
    public static Product createProduct(ProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setName(productRequestDTO.name());
        product.setNameSimplified(productRequestDTO.nameSimplified());
        product.setDescription(productRequestDTO.description());
        product.setDimension(productRequestDTO.dimension());
        product.setPathImages(productRequestDTO.pathImages());
        product.setBasePrice(productRequestDTO.basePrice());
        product.setActualPrice(productRequestDTO.actualPrice());
        product.setVersion(1);
        return product;
    }
}
