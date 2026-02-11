package com.crimson.projectred.service;

import com.crimson.projectred.constant.ErrorMessage;
import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.model.Address;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.model.Product;
import com.crimson.projectred.repositoty.AddressRepository;
import com.crimson.projectred.repositoty.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public Address createAddress(Address address, Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        address.setCustomer(customer);
        return addressRepository.save(address);
    }

    public Optional<Address> getAddressesByCustomerId(Long id) {
        return addressRepository.findById(id);
    }
}
