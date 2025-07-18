package br.ufrn.myway.Repository.Roadmap;

import java.util.List;
import java.util.Optional;

import br.ufrn.myway.model.entities.roadmap.RoadmapConcursoGeneralista;
import br.ufrn.myway.Repository.SoftDeletableRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufrn.myway.model.Enums.RoadmapStatus;

@Profile("Generalista")
@Repository
public interface RoadmapConcursoGeneralistaRepository extends SoftDeletableRepository<RoadmapConcursoGeneralista>, AbstractRoadmapRepository<RoadmapConcursoGeneralista> {

    @Query("SELECT r FROM RoadmapConcursoGeneralista r WHERE r.user.id = :id")
    List<RoadmapConcursoGeneralista> findRoadmapByUser(Long id);

    @Query("SELECT r FROM RoadmapConcursoGeneralista r WHERE r.user.id = :userId AND r.status = :status")
    Optional<RoadmapConcursoGeneralista> findByStatus(@Param("userId") Long userId, @Param("status") RoadmapStatus status);

}
