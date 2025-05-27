package br.ufrn.myway.Model.DTO.Request;

import java.util.List;

public record RequestFullGoalDTO(
        String name,
        String description,
        Long roadmapIndex,
        List<RequestFullStudyTopicDTO> studyTopics
) {
}
