package br.ufrn.myway.Model.DTO.Response;

import java.time.LocalDate;
import java.util.List;

import br.ufrn.myway.Model.DTO.GoalDTO;
import br.ufrn.myway.Model.Enums.RoadMapStatus;

public record ResponseRoadmapDTO(Long id, String type, LocalDate createdAt, String mainGoal, String description, List<GoalDTO> goals, RoadMapStatus status) {
}
