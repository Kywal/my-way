package Service;

import Entities.AbstractChronometerClass;
import Repositories.AbstractChronometerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public abstract class AbstractChronometerService<M extends AbstractChronometerClass> {

    private final AbstractChronometerRepository<M> repository;

    @Autowired
    protected AbstractChronometerService(AbstractChronometerRepository<M> repository) {
        this.repository = repository;
    }
    public M save(M entity) {
        return repository.save(entity);
    }

    public Optional<M> findById(Long id) {
        return repository.findById(id);
    }

    public List<M> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public M createChronometer(M chronometer) {
        if (chronometer == null) {
            throw new IllegalArgumentException("Chronometer não pode ser nulo");
        }

        chronometer.setTotalCycles(0);
        chronometer.setTotalHoursStudied(0);
        return repository.save(chronometer);
    }

}
