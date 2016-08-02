package ua.kay.reclamacii.services;

import java.util.List;


public interface CrudService<T> {
    T save(T obj);
    void delete(Long id);
    T getById(Long id);
    List<T> getAll();
}
