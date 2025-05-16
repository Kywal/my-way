package br.ufrn.myway.Model.DTO;

import java.time.LocalDate;

public record StudyTopicDTO(Long id, LocalDate createdAt, String name, String description, GoalDTO goal, boolean isDone) {
}
