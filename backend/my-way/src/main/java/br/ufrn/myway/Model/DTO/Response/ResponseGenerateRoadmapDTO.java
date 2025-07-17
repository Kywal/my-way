package br.ufrn.myway.Model.DTO.Response;

import java.util.List;

import br.ufrn.myway.Model.Enums.RoadMapStatus;

public record ResponseGenerateRoadmapDTO(
        String mainGoal,
        String description,
        List<ResponseGenerateGoalDTO> goals,
        RoadMapStatus status,
        String tipo
) {
}