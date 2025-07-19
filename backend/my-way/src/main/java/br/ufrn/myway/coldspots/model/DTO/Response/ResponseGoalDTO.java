package br.ufrn.myway.coldspots.model.DTO.Response;

import java.time.LocalDate;
import java.util.List;

import br.ufrn.myway.coldspots.model.enums.GoalStatus;

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
