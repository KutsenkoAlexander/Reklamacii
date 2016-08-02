package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.Defect;
import ua.kay.reclamacii.models.SprClassDefect;

import java.util.Set;

@Repository
public interface DefectRepository extends JpaRepository<Defect, Long> {
    @Async
    Defect findByIdDefect(Long idDefect);

    @Async
    Set<Defect> findBySprClassDefect(SprClassDefect sprClassDefect);
}
