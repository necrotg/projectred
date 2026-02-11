package com.crimson.projectred.service;

import lombok.RequiredArgsConstructor;
import com.crimson.projectred.model.Product;
import org.springframework.stereotype.Service;
import com.crimson.projectred.repositoty.ProductsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<Product> getAllProducts(){
        return productsRepository.findAll();
    }

    public List<Product> registerProduct(List<Product> products){
        return productsRepository.saveAll(products);
    }

}
