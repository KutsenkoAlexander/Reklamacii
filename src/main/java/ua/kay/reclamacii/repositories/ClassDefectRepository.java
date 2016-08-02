package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.SprClassDefect;

@Repository
public interface ClassDefectRepository extends JpaRepository<SprClassDefect, Long> {
    @Async
    SprClassDefect findByIdClassDefect(Long id);

    @Async
    SprClassDefect findByName(String name);
}
