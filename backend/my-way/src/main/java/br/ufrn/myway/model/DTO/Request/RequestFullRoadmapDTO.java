package br.ufrn.myway.model.DTO.Request;

import br.ufrn.myway.model.enums.RoadmapStatus;

import java.util.List;

public record RequestFullRoadmapDTO(
        String mainGoal, 
        String description, 
        List<RequestFullGoalDTO> goals,
        RoadmapStatus status
)  {
}
