package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.SprProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<SprProductType, Long> {
    @Async
    SprProductType findSprProductTypeByIdProductType(Long id);
}
