package br.ufrn.myway.Model.DTO;

import java.util.List;

public record GoalDTO(String name, String description, Long roadmapId, List<StudyTopicDTO> exercises) {
}
