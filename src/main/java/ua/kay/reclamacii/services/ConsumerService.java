package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.SprConsumer;
import ua.kay.reclamacii.repositories.ConsumerRepository;

import java.util.List;

@Service
public class ConsumerService implements CrudService<SprConsumer> {
    @Autowired
    ConsumerRepository consumerRepository;

    @Override
    public SprConsumer save(SprConsumer consumer) {
        return consumerRepository.saveAndFlush(consumer);
    }

    @Override
    public void delete(Long id) {
        consumerRepository.delete(id);
    }

    @Override
    public SprConsumer getById(Long id) {
        return consumerRepository.findSprConsumerByIdConsumer(id);
    }

    @Override
    public List<SprConsumer> getAll() {
        return consumerRepository.findAll();
    }

    public List<SprConsumer> findConsumerByName(String params){
        return consumerRepository.findByNameStartingWithIgnoreCase(params);
    }
}
