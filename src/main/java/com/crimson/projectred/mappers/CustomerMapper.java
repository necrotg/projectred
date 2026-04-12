package com.crimson.projectred.mappers;

import com.crimson.projectred.dto.CustomerDTO;
import com.crimson.projectred.model.Customer;

public class CustomerMapper {
    public Customer mapCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.firstName());
        customer.setSurName(customerDTO.surName());
        customer.setPhoneNumber(customerDTO.phoneNumber());
        customer.setEmail(customerDTO.email());
        customer.setCpf(customerDTO.cpf());
        return customer;
    }
}
