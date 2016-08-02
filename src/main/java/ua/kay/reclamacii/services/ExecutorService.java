package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.SprExecutor;
import ua.kay.reclamacii.repositories.ExecutorRepository;

import java.util.List;

@Service
public class ExecutorService implements CrudService<SprExecutor> {
    @Autowired
    ExecutorRepository executorRepository;

    @Override
    public SprExecutor save(SprExecutor obj) {
        return executorRepository.saveAndFlush(obj);
    }

    @Override
    public void delete(Long id) {
        executorRepository.delete(id);
    }

    @Override
    public SprExecutor getById(Long id) {
        return executorRepository.findByIdExecutor(id);
    }

    @Override
    public List<SprExecutor> getAll() {
        return executorRepository.findAll();
    }

    public List<SprExecutor> findExecutorByTypedName(String params){
        return executorRepository.findByNameStartingWithIgnoreCase(params);
    }
}
