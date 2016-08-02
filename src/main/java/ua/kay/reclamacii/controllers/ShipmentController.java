package ua.kay.reclamacii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.reclamacii.models.Shipment;
import ua.kay.reclamacii.services.ShipmentService;

import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    @Autowired
    ShipmentService shipmentService;

    @RequestMapping("/all")
    public List<Shipment> findAllShipments(){
        return shipmentService.getAll();
    }

    @RequestMapping("/{id}")
    public Shipment findShipmentById(@PathVariable("id") Long id){
        return shipmentService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Shipment saveShipment(@RequestBody Shipment shipment) {
        return shipmentService.save(shipment);
    }
}
