package Repositories;

import Entities.AbstractChronometerClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean

public interface AbstractChronometerRepository<M extends AbstractChronometerClass>
        extends JpaRepository<M, Long> {

}
