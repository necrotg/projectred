package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.constant.ResponseMessage;
import com.crimson.projectred.dto.CustomerDTO;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.exception.cust.NotFoundException;
import com.crimson.projectred.factory.CustomerFactory;
import com.crimson.projectred.mappers.CustomerMapper;
import com.crimson.projectred.model.Cart;
import com.crimson.projectred.model.Wishlist;
import lombok.RequiredArgsConstructor;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.model.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.crimson.projectred.repository.CustomerRepository;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDTO customerDTO){
        if(customerRepository.findByEmailIgnoreCase(customerDTO.email()).isPresent()){
            throw new BusinessException(ExceptionMessage.EMAIL_EXISTS_MESSAGE);
        }
        Customer customer = CustomerFactory.createCustomer(customerDTO);
        return customerRepository.save(customer);
    }
    public Customer getCustomerByEmail(String customer){
        return customerRepository.findByEmailIgnoreCase(customer)
                .orElseThrow(()-> new NotFoundException(ExceptionMessage.CUSTOMER_NOT_FOUND));
    }
    public Customer getCustomerById(Long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(()->new BusinessException(ExceptionMessage.CUSTOMER_ID_NOT_VALID));
    }
    public ResponseEntity<StandardResponse> deleteCustomerById(Long id){
        if(!customerRepository.existsById(id)){
            throw new NotFoundException(ExceptionMessage.CUSTOMER_NOT_FOUND);
        }
        customerRepository.deleteById(id);
        StandardResponse response = new StandardResponse(new Date().getTime(), HttpStatus.OK.value(), ResponseMessage.CUSTOMER_DELETED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
