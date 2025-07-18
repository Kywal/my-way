package br.ufrn.myway.model.DTO.Request;

import br.ufrn.myway.model.Enums.GoalStatus;

public record RequestStaticGoalDTO(String name, String description, Long roadmapIndex, GoalStatus status) {

}
