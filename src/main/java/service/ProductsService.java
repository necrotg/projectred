package service;

import lombok.RequiredArgsConstructor;
import model.Product;
import org.springframework.stereotype.Service;
import repositoty.ProductsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<Product> getAllProducts(){
       return productsRepository.findAll();
    }
    public Product registerProduct(Product product){
        return productsRepository.save(product);
    }

}
