package br.ufrn.myway.hotspots.ConcursoProfessor.RoadmapConcursoProfessor.repository;

import java.util.List;
import java.util.Optional;

import br.ufrn.myway.coldspots.model.entities.roadmap.RoadmapConcursoProfessor;
import br.ufrn.myway.coldspots.model.enums.RoadmapStatus;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufrn.myway.coldspots.Common.repository.SoftDeletableRepository;
import br.ufrn.myway.coldspots.Roadmap.repository.AbstractRoadmapRepository;


@Profile("Professor")
@Repository
public interface RoadmapConcursoProfessorRepository extends SoftDeletableRepository<RoadmapConcursoProfessor>, AbstractRoadmapRepository<RoadmapConcursoProfessor> {
   
    @Override
    @Query("SELECT r FROM RoadmapConcursoPoliciaCivil r WHERE r.user.id = :id")
    List<RoadmapConcursoProfessor> findRoadmapByUser(Long id);

    @Override
    @Query("SELECT r FROM RoadmapConcursoGeneralista r WHERE r.user.id = :userId AND r.status = :status")
    Optional<RoadmapConcursoProfessor> findByStatus(@Param("userId") Long userId, @Param("status") RoadmapStatus status);

}
