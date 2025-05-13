package br.ufrn.myway.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufrn.myway.Model.Entities.Mission;


@Repository
public interface MissionRepository extends SoftDeletableRepository<Mission> {
    
    List<Mission> findByTitle(String title);

    List<Mission> findByCompleted(boolean completed);

    List<Mission> findByStartDate(LocalDateTime startDate);


}
