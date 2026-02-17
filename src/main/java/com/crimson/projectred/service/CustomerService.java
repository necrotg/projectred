package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.constant.ResponseMessage;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.model.Cart;
import com.crimson.projectred.model.Wishlist;
import lombok.RequiredArgsConstructor;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.model.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.crimson.projectred.repositoty.CustomerRepository;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        if(customerRepository.findByEmailIgnoreCase(customer.getEmail()).isPresent()){
            throw new BusinessException(ExceptionMessage.EMAIL_EXISTS_MESSAGE);
        }
        return customerRepository.save(customer);
    }
    public Customer getCustomerByEmail(String customer){
        return customerRepository.findByEmailIgnoreCase(customer)
                .orElseThrow(()-> new BusinessException(ExceptionMessage.CUSTOMER_NOT_FOUND));
    }
    public Customer getCustomerById(Long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(()->new BusinessException(ExceptionMessage.CUSTOMER_ID_NOT_VALID));
    }
    public ResponseEntity<StandardResponse> deleteCustomerById(Long id){
        if(!customerRepository.existsById(id)){
            throw new BusinessException(ExceptionMessage.CUSTOMER_NOT_FOUND);
        }
        customerRepository.deleteById(id);
        StandardResponse response = new StandardResponse(new Date().getTime(), HttpStatus.OK.value(), ResponseMessage.CUSTOMER_DELETED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
