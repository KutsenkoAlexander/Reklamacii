package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.DefectSolution;
import ua.kay.reclamacii.repositories.DefectSolutionRepository;

import java.util.List;

@Service
public class DefectSolutionService implements CrudService<DefectSolution> {
    @Autowired
    DefectSolutionRepository defectSolutionsRepository;

    @Override
    public DefectSolution save(DefectSolution obj) {
        return defectSolutionsRepository.saveAndFlush(obj);
    }

    @Override
    public void delete(Long id) {
        defectSolutionsRepository.delete(id);
    }

    @Override
    public DefectSolution getById(Long id) {
        return defectSolutionsRepository.findByIdDefectSolution(id);
    }

    @Override
    public List<DefectSolution> getAll() {
        return defectSolutionsRepository.findAll();
    }

    public List<DefectSolution> saveDefectSolutions(List<DefectSolution> defectSolutionsList){
        return defectSolutionsRepository.save(defectSolutionsList);
    }
}
