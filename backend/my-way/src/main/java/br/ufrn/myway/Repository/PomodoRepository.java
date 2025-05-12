package Repositories;

import Entities.PomodoroClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PomodoRepository extends JpaRepository<PomodoroClass,Long > {
}
