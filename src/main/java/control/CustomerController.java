package control;

import exception.handler.ResourceExceptionHandler;
import lombok.RequiredArgsConstructor;
import model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private ResourceExceptionHandler resourceExceptionHandler;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(Customer customer){
        Customer savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
    @GetMapping
    public ResponseEntity<Customer> getCustomerByEmail(String email){
        Customer customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
}
