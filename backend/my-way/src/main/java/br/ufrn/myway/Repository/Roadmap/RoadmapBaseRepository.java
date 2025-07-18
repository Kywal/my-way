package br.ufrn.myway.Repository.Roadmap;

import br.ufrn.myway.model.entities.roadmap.RoadmapBase;
import br.ufrn.myway.Repository.SoftDeletableRepository;

public interface RoadmapBaseRepository extends SoftDeletableRepository<RoadmapBase>, AbstractRoadmapRepository<RoadmapBase> {

}
