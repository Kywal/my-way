package br.ufrn.myway.Model.DTO.Request;

import br.ufrn.myway.Model.Enums.RoadmapStatus;

import java.util.List;

public record RequestFullRoadmapDTO(
        String mainGoal, 
        String description, 
        List<RequestFullGoalDTO> goals,
        RoadmapStatus status
)  {
}
