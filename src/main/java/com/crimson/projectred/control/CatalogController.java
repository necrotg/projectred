package com.crimson.projectred.control;

import com.crimson.projectred.exception.handler.ResourceExceptionHandler;
import lombok.RequiredArgsConstructor;
import com.crimson.projectred.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crimson.projectred.service.ProductsService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class CatalogController {

    private final ProductsService productsService;

    @PostMapping
    public ResponseEntity<List<Product>> registerProduct(@RequestBody List<Product> products){
        List<Product> savedProduct = productsService.registerProduct(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productsService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
