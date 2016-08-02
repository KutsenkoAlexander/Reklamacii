package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.SprConsumer;

import java.util.List;

@Repository
public interface ConsumerRepository extends JpaRepository<SprConsumer, Long> {
    @Async
    SprConsumer findSprConsumerByIdConsumer(Long id);
    @Async
    List<SprConsumer> findByNameStartingWithIgnoreCase(String name);
}
