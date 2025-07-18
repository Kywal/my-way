package br.ufrn.myway.Repository.Roadmap;

import br.ufrn.myway.Repository.SoftDeletableRepository;
import br.ufrn.myway.model.Enums.RoadmapStatus;
import br.ufrn.myway.model.entities.roadmap.RoadmapConcursoPoliciaCivil;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Profile("Policia-civil")
@Repository
public interface RoadmapConcursoPoliciaCivilRepository extends SoftDeletableRepository<RoadmapConcursoPoliciaCivil>, AbstractRoadmapRepository<RoadmapConcursoPoliciaCivil> {
    
    @Query("SELECT r FROM RoadmapConcursoPoliciaCivil r WHERE r.user.id = :id")
    List<RoadmapConcursoPoliciaCivil> findRoadmapByUser(Long id);

    @Query("SELECT r FROM RoadmapConcursoGeneralista r WHERE r.user.id = :userId AND r.status = :status")
    Optional<RoadmapConcursoPoliciaCivil> findByStatus(@Param("userId") Long userId, @Param("status") RoadmapStatus status);
    
}
