package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.Shipment;
import ua.kay.reclamacii.repositories.ShipmentRepository;

import java.util.List;

@Service
public class ShipmentService implements CrudService<Shipment> {
    @Autowired
    ShipmentRepository shipmentRepository;

    @Override
    public Shipment save(Shipment obj) {
        return shipmentRepository.saveAndFlush(obj);
    }

    @Override
    public void delete(Long id) {
        shipmentRepository.delete(id);
    }

    @Override
    public Shipment getById(Long id) {
        return shipmentRepository.findByIdShipment(id);
    }

    @Override
    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }
}
