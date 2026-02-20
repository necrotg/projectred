package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.model.ShipmentOption;
import com.crimson.projectred.repositoty.ShipmentOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentOptionsService {

    private final ShipmentOptionsRepository shipmentOptionsRepository;

    public List<ShipmentOption> getShipmentOptions(){
        return shipmentOptionsRepository.findAll();
    }

    public void createShipmentOptions(List<ShipmentOption> shipmentOptions){
        shipmentOptionsRepository.saveAll(shipmentOptions);
    }

    public ShipmentOption getShipmentOptionById(Long id){
        return shipmentOptionsRepository.findById(id).orElseThrow(()->new BusinessException(ExceptionMessage.SHIPMENT_OPTION_NOT_FOUND));
    }
}
