package com.crimson.projectred.control;

import com.crimson.projectred.exception.handler.ResourceExceptionHandler;
import com.crimson.projectred.model.Address;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.model.Product;
import com.crimson.projectred.service.AddressService;
import com.crimson.projectred.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer/{customerId}/addresses/")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address,@PathVariable Long customerId){
        Address savedAddress = addressService.createAddress(address,customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }
    @GetMapping
    public ResponseEntity<Optional<Address>> getAddressesByCustomerId(@PathVariable Long id){
        Optional<Address> addresses = addressService.getAddressesByCustomerId(id);
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }
}