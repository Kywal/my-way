package br.ufrn.myway.coldspots.model.DTO.Request;

import java.util.List;

import br.ufrn.myway.coldspots.model.DTO.Response.ResponseGenerateGoalDTO;

public record RequestRoadmapDTO(
        String mainGoal,
        String description,
        List<ResponseGenerateGoalDTO> goals,
        Long concursoId
) {

}
