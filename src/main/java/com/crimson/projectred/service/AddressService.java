package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.exception.cust.NotFoundException;
import com.crimson.projectred.model.Address;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.repository.AddressRepository;
import com.crimson.projectred.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private static final Logger log = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void addAddress(Address address, Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        customer.getAddresses().add(address);
        address.setCustomer(customer);
        log.info("AddressService::addAddress: Address Added Successfully");
    }

    public List<Address> getAddressesByCustomerId(Long customerId) {
        List<Address> address = addressRepository.findAddressByCustomer_CustomerId(customerId);
        if(address.isEmpty()){
            log.  info("AddressService::addAddress: Address not found for Customer ID: "+customerId);
            throw new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND);
        }
        log.info("AddressService::getAddressesByCustomerId: Address found for Customer ID: "+customerId);
        return address;
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND));
    }
}
