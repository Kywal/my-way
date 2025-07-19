package br.ufrn.myway.coldspots.model.DTO.Response;

import java.util.List;

import br.ufrn.myway.coldspots.model.enums.RoadmapStatus;

public record ResponseGenerateRoadmapDTO(
        String mainGoal,
        String description,
        List<ResponseGenerateGoalDTO> goals,
        RoadmapStatus status,
        String tipo
) {
}