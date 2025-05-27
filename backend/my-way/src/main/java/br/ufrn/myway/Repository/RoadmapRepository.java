package br.ufrn.myway.Repository;

import br.ufrn.myway.Model.Entities.Roadmap;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoadmapRepository extends SoftDeletableRepository<Roadmap> {
    @Query("SELECT r FROM Roadmap r WHERE r.user.id = :id")
    List<Roadmap> findRoadMapByUser(Long id);
}
