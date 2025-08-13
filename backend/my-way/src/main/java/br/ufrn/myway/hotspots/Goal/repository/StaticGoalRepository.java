package br.ufrn.myway.hotspots.Goal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import br.ufrn.myway.coldspots.model.entities.Goal.GoalBase;

@Repository
public interface GoalRepository extends JpaRepository<GoalBase, Long> { 
}
