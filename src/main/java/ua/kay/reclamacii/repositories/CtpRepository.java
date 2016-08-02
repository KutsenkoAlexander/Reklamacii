package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.Ctp;
import ua.kay.reclamacii.models.SprProductType;

@Repository
public interface CtpRepository extends JpaRepository<Ctp, Long> {
    @Async
    Ctp findByIdCtp(Long idCtp);

    @Async
    Ctp findByProductType(SprProductType productType);
}
