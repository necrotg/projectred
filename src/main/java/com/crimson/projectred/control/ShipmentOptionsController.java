package com.crimson.projectred.control;

import com.crimson.projectred.model.ShipmentOption;
import com.crimson.projectred.service.ShipmentOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipment-options")
@RequiredArgsConstructor
public class ShipmentOptionsController {

    private final ShipmentOptionsService shipmentOptionsService;

    @GetMapping
    public ResponseEntity<List<ShipmentOption>> getShipmentOptions() {
       List<ShipmentOption> shipmentOptions = shipmentOptionsService.getShipmentOptions();
       return ResponseEntity.status(HttpStatus.OK).body(shipmentOptions);
    }
    @PostMapping
    public ResponseEntity<List<ShipmentOption>> createShipmentOption(@RequestBody List<ShipmentOption> shipmentOptions) {
        shipmentOptionsService.createShipmentOptions(shipmentOptions);
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentOptions);
    }
}
