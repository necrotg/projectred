package control;

import exception.handler.ResourceExceptionHandler;
import lombok.RequiredArgsConstructor;
import model.Customer;
import model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CustomerService;
import service.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class CatalogController {

    private final ProductsService productsService;
    private ResourceExceptionHandler resourceExceptionHandler;

    @PostMapping
    public ResponseEntity<Product> registerProduct(Product product){
        Product savedProduct = productsService.registerProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productsService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
