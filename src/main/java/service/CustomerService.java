package service;

import constant.ExceptionMessage;
import exception.cust.BusinessException;
import lombok.RequiredArgsConstructor;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositoty.CustomerRepository;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        if(Objects.nonNull(customerRepository.findByEmailIgnoreCase(customer.getEmail()))){
            throw new BusinessException(ExceptionMessage.EMAIL_EXISTS_MESSAGE);
        }
        return customerRepository.save(customer);
    }
    public Customer getCustomerByEmail(String customer){

        return customerRepository.findByEmailIgnoreCase(customer);
    }
}
