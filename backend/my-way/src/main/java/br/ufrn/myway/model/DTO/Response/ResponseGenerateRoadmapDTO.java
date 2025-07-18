package br.ufrn.myway.model.DTO.Response;

import java.util.List;

import br.ufrn.myway.model.Enums.RoadmapStatus;

public record ResponseGenerateRoadmapDTO(
        String mainGoal,
        String description,
        List<ResponseGenerateGoalDTO> goals,
        RoadmapStatus status,
        String tipo
) {
}