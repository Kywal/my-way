package br.ufrn.myway.Model.DTO.Response;

import br.ufrn.myway.Model.Enums.RoadMapStatus;

import java.time.LocalDate;
import java.util.List;

public record ResponseRoadmapDTO(Long id, LocalDate createdAt, String mainGoal, String description, List<ResponseGoalDTO> goals, RoadMapStatus status) {
}
