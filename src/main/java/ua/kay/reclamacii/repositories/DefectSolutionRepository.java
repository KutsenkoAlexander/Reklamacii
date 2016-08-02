package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.DefectSolution;

@Repository
public interface DefectSolutionRepository extends JpaRepository<DefectSolution, Long> {
    @Async
    DefectSolution findByIdDefectSolution(Long idDefectSolution);
}
