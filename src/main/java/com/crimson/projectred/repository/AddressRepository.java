package com.crimson.projectred.repository;

import com.crimson.projectred.model.Address;
import com.crimson.projectred.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findAddressByCustomer_CustomerId(Long customerId);
}
