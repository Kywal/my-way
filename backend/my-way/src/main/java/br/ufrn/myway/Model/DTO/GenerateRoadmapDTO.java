package br.ufrn.myway.Model.DTO;

import br.ufrn.myway.Model.Enums.RoadMapStatus;

import java.util.List;

public record GenerateRoadmapDTO(
        String mainGoal, 
        String description, 
        List<GenerateGoalDTO> goals,
        RoadMapStatus status
)  {
}
