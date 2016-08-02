package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.Ctp;
import ua.kay.reclamacii.repositories.CtpRepository;

import java.util.List;

@Service
public class CtpService implements CrudService<Ctp> {
    @Autowired
    CtpRepository ctpRepository;

    @Override
    public Ctp save(Ctp obj) {
        return ctpRepository.saveAndFlush(obj);
    }

    @Override
    public void delete(Long id) {
        ctpRepository.delete(id);
    }

    @Override
    public Ctp getById(Long id) {
        return ctpRepository.findByIdCtp(id);
    }

    @Override
    public List<Ctp> getAll() {
        return ctpRepository.findAll();
    }
}
