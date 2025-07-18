package br.ufrn.myway.Service.RoadmapService;

import br.ufrn.myway.model.enums.RoadmapStatus;

import java.util.List;

public interface AbstractRoadmapService<RoadmapType> {

    public RoadmapType save(RoadmapType roadmapType, Long userId);

    public RoadmapType findById(Long userId);

    public List<RoadmapType> findAll();

    public void remove(Long id);

    public List<RoadmapType> findRoadmapByUser(Long userId);

    public RoadmapType cancelRoadmap(Long roadmapId);

    public RoadmapType finishRoadmap(Long id);

    public RoadmapType getByStatus(Long userId, RoadmapStatus roadmapStatus);

}
