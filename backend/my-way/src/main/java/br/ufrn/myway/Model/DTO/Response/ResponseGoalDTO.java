package br.ufrn.myway.Model.DTO.Response;

import java.time.LocalDate;
import java.util.List;

import br.ufrn.myway.Model.Enums.GoalStatus;

public record ResponseGoalDTO(
        Long id,
        LocalDate createdAt,
        String name,
        String description,
        Long roadmapId,
        Long roadmapIndex,
        List<ResponseStudyTopicDTO> studyTopics,
        GoalStatus status
        ) {
}
