package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.dto.ProductRequestDTO;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.exception.cust.NotFoundException;
import com.crimson.projectred.factory.ProductFactory;
import lombok.RequiredArgsConstructor;
import com.crimson.projectred.model.Product;
import org.springframework.stereotype.Service;
import com.crimson.projectred.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<Product> getAllProducts(){
        return productsRepository.findAll();
    }

    public List<Product> registerProducts(List<ProductRequestDTO> productsRequestDTO){
        List<Product> products = new ArrayList<>();
        productsRequestDTO.forEach(productRequestDTO -> products.add(ProductFactory.createProduct(productRequestDTO)));
        return productsRepository.saveAll(products);
    }

    public Product getProductById(Long productId){
        return productsRepository.findById(productId).orElseThrow(()-> new NotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND));
    }

}
