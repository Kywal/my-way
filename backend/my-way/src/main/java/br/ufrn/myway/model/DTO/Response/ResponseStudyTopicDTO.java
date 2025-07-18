package br.ufrn.myway.model.DTO.Response;

import java.time.LocalDate;

import br.ufrn.myway.model.enums.StudyTopicStatus;

public record ResponseStudyTopicDTO(
        Long id,
        LocalDate createdAt,
        String name,
        String description,
        Long goalId,
        StudyTopicStatus status
) {
}
