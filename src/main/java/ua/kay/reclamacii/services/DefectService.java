package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.Defect;
import ua.kay.reclamacii.models.SprClassDefect;
import ua.kay.reclamacii.repositories.DefectRepository;

import java.util.List;
import java.util.Set;

@Service
public class DefectService implements CrudService<Defect> {
    @Autowired
    DefectRepository defectRepository;

    @Override
    public Defect save(Defect obj) {
        return defectRepository.saveAndFlush(obj);
    }

    @Override
    public void delete(Long id) {
        defectRepository.delete(id);
    }

    @Override
    public Defect getById(Long id) {
        return defectRepository.findByIdDefect(id);
    }

    @Override
    public List<Defect> getAll() {
        return defectRepository.findAll();
    }

    public List<Defect> saveList(List<Defect> defectList){
        return defectRepository.save(defectList);
    }

    public Set<Defect> getDefectByClassDefect (SprClassDefect sprClassDefect){
        return defectRepository.findBySprClassDefect(sprClassDefect);
    }
}
