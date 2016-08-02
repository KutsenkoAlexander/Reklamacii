package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.SprExecutor;

import java.util.List;

@Repository
public interface ExecutorRepository extends JpaRepository<SprExecutor, Long> {
    @Async
    SprExecutor findByIdExecutor(Long idExecutor);
    @Async
    List<SprExecutor> findByNameStartingWithIgnoreCase(String name);
}
