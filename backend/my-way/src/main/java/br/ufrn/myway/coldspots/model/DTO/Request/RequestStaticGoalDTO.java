package br.ufrn.myway.coldspots.model.DTO.Request;

import br.ufrn.myway.coldspots.model.enums.GoalStatus;

public record RequestStaticGoalDTO(String name, String description, Long roadmapIndex, GoalStatus status) {

}
