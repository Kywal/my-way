package br.ufrn.myway.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import br.ufrn.myway.Model.Entities.Goal.GoalBase;

@Repository
public interface GoalRepository extends JpaRepository<GoalBase, Long> { 
}
