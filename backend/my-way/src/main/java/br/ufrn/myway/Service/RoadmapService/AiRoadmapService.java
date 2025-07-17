package br.ufrn.myway.Service.RoadmapService;

import br.ufrn.myway.Model.DTO.Response.ResponseGenerateRoadmapDTO;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;

public abstract class AiRoadmapService implements AbstractAiRoadmapService {
    
    @Override
    public ResponseGenerateRoadmapDTO generateRoadmap(String mainGoal, String description, String aditionalInfo) {
        // Implementation for generating a roadmap
        return null; // Placeholder return
    }

    @Override
    public RoadmapBase saveGeneratedRoadmap(RoadmapBase roadmap, Long userId) {
        // Implementation for saving the generated roadmap
        return null; // Placeholder return
    }

    @Override
    public RoadmapBase generateAndSaveRoadmap(String mainGoal, String description, Long userId) {
        // Implementation for generating and saving a roadmap
        return null; // Placeholder return
    }
    
}
