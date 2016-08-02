package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.SprProductType;
import ua.kay.reclamacii.repositories.ProductTypeRepository;

import java.util.List;

@Service
public class ProductTypeService implements CrudService<SprProductType> {
    @Autowired
    ProductTypeRepository sprProductTypeRepository;

    @Override
    public SprProductType save(SprProductType obj) {
        return sprProductTypeRepository.saveAndFlush(obj);
    }

    @Override
    public void delete(Long id) {
        sprProductTypeRepository.delete(id);
    }

    @Override
    public SprProductType getById(Long id) {
        return sprProductTypeRepository.findSprProductTypeByIdProductType(id);
    }

    @Override
    public List<SprProductType> getAll() {
        return sprProductTypeRepository.findAll();
    }
}
