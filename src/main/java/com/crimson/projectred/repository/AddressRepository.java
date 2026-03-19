package com.crimson.projectred.repository;

import com.crimson.projectred.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
