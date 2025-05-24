package br.ufrn.myway.Model.DTO.Response;

import br.ufrn.myway.Model.DTO.GoalDTO;
import br.ufrn.myway.Model.Enums.RoadMapStatus;

import java.util.List;

public record ResponseRoadmapDTO(String name, List<GoalDTO> goals, RoadMapStatus status) {
}
