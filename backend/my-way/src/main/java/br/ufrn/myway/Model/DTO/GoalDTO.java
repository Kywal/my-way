package br.ufrn.myway.Model.DTO;

import java.time.LocalDate;
import java.util.List;

public record GoalDTO(Long id, LocalDate createdAt, String name, RoadMapDTO roadmap, int size, List<StudyTopicDTO> exercises) {
}
