package br.ufrn.myway.coldspots.Roadmap.repository;

import br.ufrn.myway.model.entities.roadmap.RoadmapBase;
import br.ufrn.myway.coldspots.Common.repository.SoftDeletableRepository;

public interface RoadmapBaseRepository extends SoftDeletableRepository<RoadmapBase>, AbstractRoadmapRepository<RoadmapBase> {

}
