package br.ufrn.myway.Model.DTO.Response;

import java.time.LocalDate;
import java.util.List;

import br.ufrn.myway.Model.DTO.GoalDTO;
import br.ufrn.myway.Model.Enums.RoadmapStatus;

public record ResponseRoadmapDTO(Long id, String type, LocalDate createdAt, String mainGoal, String description, List<GoalDTO> goals, RoadmapStatus status) {
}
