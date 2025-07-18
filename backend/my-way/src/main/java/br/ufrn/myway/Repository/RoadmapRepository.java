package br.ufrn.myway.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;
import br.ufrn.myway.Model.Enums.RoadmapStatus;

@Repository
public interface RoadmapRepository extends SoftDeletableRepository<RoadmapBase> {
    @Query("SELECT r FROM Roadmap r WHERE r.user.id = :id")
    List<RoadmapBase> findRoadMapByUser(Long id);

    @Query("SELECT r FROM Roadmap r WHERE r.user.id = :userId AND r.status = :status")
    RoadmapBase findByStatus(@Param("userId") Long userId, @Param("status") RoadmapStatus status);

}
