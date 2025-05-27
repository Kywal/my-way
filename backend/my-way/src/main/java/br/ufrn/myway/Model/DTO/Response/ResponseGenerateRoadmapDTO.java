package br.ufrn.myway.Model.DTO.Response;

import br.ufrn.myway.Model.Enums.RoadMapStatus;

import java.util.List;

public record ResponseGenerateRoadmapDTO(
        String mainGoal,
        String description,
        List<ResponseGenerateGoalDTO> goals,
        RoadMapStatus status
) {
}
