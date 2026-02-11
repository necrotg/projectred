package com.crimson.projectred.repositoty;

import com.crimson.projectred.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
