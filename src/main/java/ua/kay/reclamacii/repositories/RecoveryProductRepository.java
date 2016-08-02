package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.RecoveryProduct;

@Repository
public interface RecoveryProductRepository extends JpaRepository<RecoveryProduct, Long> {
    @Async
    RecoveryProduct findByIdRecoveryProduct(Long idRecoveryProduct);
}
