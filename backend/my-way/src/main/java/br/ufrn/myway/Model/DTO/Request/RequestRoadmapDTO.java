package br.ufrn.myway.Model.DTO.Request;

import java.util.List;

import br.ufrn.myway.Model.DTO.Response.ResponseGenerateGoalDTO;

public record RequestRoadmapDTO(String mainGoal, String description, List<ResponseGenerateGoalDTO> goals, Long concursoId) {

}
