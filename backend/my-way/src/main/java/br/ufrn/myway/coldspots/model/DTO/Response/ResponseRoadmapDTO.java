package br.ufrn.myway.coldspots.model.DTO.Response;

import java.time.LocalDate;
import java.util.List;

import br.ufrn.myway.coldspots.model.DTO.GoalDTO;
import br.ufrn.myway.coldspots.model.enums.RoadmapStatus;

public record ResponseRoadmapDTO(Long id, String type, LocalDate createdAt, String mainGoal, String description, List<GoalDTO> goals, RoadmapStatus status) {
}
