package br.ufrn.myway.Repository;

import br.ufrn.myway.Model.Entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends SoftDeletableRepository<Goal> {
}
