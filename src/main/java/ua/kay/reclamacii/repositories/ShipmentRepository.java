package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    @Async
    Shipment findByIdShipment(Long idShipment);
}
