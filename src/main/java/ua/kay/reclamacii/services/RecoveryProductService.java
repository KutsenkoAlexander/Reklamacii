package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.RecoveryProduct;
import ua.kay.reclamacii.repositories.RecoveryProductRepository;

import java.util.List;

@Service
public class RecoveryProductService implements CrudService<RecoveryProduct> {
    @Autowired
    RecoveryProductRepository recoveryProductRepository;

    @Override
    public RecoveryProduct save(RecoveryProduct obj) {
        return recoveryProductRepository.saveAndFlush(obj);
    }

    @Override
    public void delete(Long id) {
        recoveryProductRepository.delete(id);
    }

    @Override
    public RecoveryProduct getById(Long id) {
        return recoveryProductRepository.findByIdRecoveryProduct(id);
    }

    @Override
    public List<RecoveryProduct> getAll() {
        return recoveryProductRepository.findAll();
    }
}
