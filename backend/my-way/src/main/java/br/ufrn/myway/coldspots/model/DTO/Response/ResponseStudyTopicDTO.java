package br.ufrn.myway.coldspots.model.DTO.Response;

import java.time.LocalDate;

import br.ufrn.myway.coldspots.model.enums.StudyTopicStatus;

public record ResponseStudyTopicDTO(
        Long id,
        LocalDate createdAt,
        String name,
        String description,
        Long goalId,
        StudyTopicStatus status
) {
}
