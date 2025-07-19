package br.ufrn.myway.hotspots.ConcursoGeneralista.RoadmapConcursoGeneralista.repository;

import br.ufrn.myway.coldspots.Common.repository.SoftDeletableRepository;
import br.ufrn.myway.coldspots.Roadmap.repository.AbstractRoadmapRepository;
import br.ufrn.myway.coldspots.model.entities.roadmap.RoadmapConcursoGeneralista;
import br.ufrn.myway.coldspots.model.enums.RoadmapStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoadmapConcursoGeneralistaRepository extends SoftDeletableRepository<RoadmapConcursoGeneralista>, AbstractRoadmapRepository<RoadmapConcursoGeneralista> {

    @Query("SELECT r FROM RoadmapConcursoGeneralista r WHERE r.user.id = :id")
    List<RoadmapConcursoGeneralista> findRoadmapByUser(Long id);

    @Query("SELECT r FROM RoadmapConcursoGeneralista r WHERE r.user.id = :userId AND r.status = :status")
    Optional<RoadmapConcursoGeneralista> findByStatus(@Param("userId") Long userId, @Param("status") RoadmapStatus status);

}
