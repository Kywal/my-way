package br.ufrn.myway.Model.DTO.Response;

import java.time.LocalDate;

public record ResponseStudyTopicDTO(
        Long id,
        LocalDate createdAt,
        String name,
        String description,
        Long goalId,
        boolean isDone
) {
}
