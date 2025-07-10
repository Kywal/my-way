package br.ufrn.myway.Model.DTO.Request;

import java.util.List;

import br.ufrn.myway.Model.DTO.GoalDTO;

public record RequestRoadmapDTO(String mainGoal, String description, List<GoalDTO> goals, Long concursoId) {

}
