package br.ufrn.myway.Model.DTO.Request;

import br.ufrn.myway.Model.Enums.GoalStatus;

public record RequestStaticGoalDTO(String name, String description, Long roadmapIndex, GoalStatus status) {

}
