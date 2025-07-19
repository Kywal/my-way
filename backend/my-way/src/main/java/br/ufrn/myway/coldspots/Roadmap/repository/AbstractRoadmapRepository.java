package br.ufrn.myway.coldspots.Roadmap.repository;

import br.ufrn.myway.coldspots.model.enums.RoadmapStatus;

import java.util.List;
import java.util.Optional;

public interface AbstractRoadmapRepository<RoadmapType> {

    public RoadmapType save(RoadmapType roadmapType);

    public void delete(Long id);

    public List<RoadmapType> findAll();

    public Optional<RoadmapType> findById(Long id);

    List<RoadmapType> findRoadmapByUser(Long id);

    Optional<RoadmapType> findByStatus(Long userId, RoadmapStatus status);

}
