package br.ufrn.myway.Model.DTO.Response;

import java.time.LocalDate;

import br.ufrn.myway.Model.Enums.StudyTopicStatus;

public record ResponseStudyTopicDTO(
        Long id,
        LocalDate createdAt,
        String name,
        String description,
        Long goalId,
        StudyTopicStatus status
) {
}
