package testtask.shift.shopapi.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public abstract class BaseCrudService<T> {

    protected final CrudRepository<T, Long> repository;
    protected final String entityName;

    protected BaseCrudService(CrudRepository<T, Long> repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }

    public Iterable<T> findAll() {
        return repository.findAll();
    }

    public T findById(long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(entityName + " not found"));
    }

    public T save(T entity) {
        return repository.save(entity);
    }
}
