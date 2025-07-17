package br.ufrn.myway.Service.RoadmapService;

import br.ufrn.myway.Model.DTO.Response.ResponseGenerateRoadmapDTO;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;

public interface AbstractAiRoadmapService {
    ResponseGenerateRoadmapDTO generateRoadmap(String mainGoal, String description, String aditionalInfo);
    RoadmapBase saveGeneratedRoadmap(RoadmapBase roadmap, Long userId);
    RoadmapBase generateAndSaveRoadmap(String mainGoal, String description, Long userId);
}
