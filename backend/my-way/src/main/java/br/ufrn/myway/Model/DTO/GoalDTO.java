package br.ufrn.myway.Model.DTO;

import java.time.LocalDate;
import java.util.List;

public record GoalDTO(String name, Long roadmapId, int size, List<StudyTopicDTO> exercises) {
}
