package br.ufrn.myway.Repository;

import br.ufrn.myway.Model.Entities.Roadmap;
import br.ufrn.myway.Model.Enums.RoadMapStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoadmapRepository extends SoftDeletableRepository<Roadmap> {
    @Query("SELECT r FROM Roadmap r WHERE r.user.id = :id")
    List<Roadmap> findRoadMapByUser(Long id);

    @Query("SELECT r FROM Roadmap r WHERE r.user.id = :userId AND r.status = :status")
    Roadmap findByStatus(@Param("id") Long userId, @Param("status") RoadMapStatus status);

}
