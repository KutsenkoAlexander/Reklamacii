package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.SprClassDefect;
import ua.kay.reclamacii.repositories.ClassDefectRepository;

import java.util.List;

@Service
public class ClassDefectService implements CrudService<SprClassDefect> {
    @Autowired
    ClassDefectRepository classDefectRepository;

    @Override
    public SprClassDefect save(SprClassDefect obj) {
        return classDefectRepository.saveAndFlush(obj);
    }

    @Override
    public void delete(Long id) {
        classDefectRepository.delete(id);
    }

    @Override
    public SprClassDefect getById(Long id) {
        return classDefectRepository.findByIdClassDefect(id);
    }

    @Override
    public List<SprClassDefect> getAll() {
        return classDefectRepository.findAll();
    }

    public SprClassDefect getByName(String name) {
        return classDefectRepository.findByName(name);
    }
}
