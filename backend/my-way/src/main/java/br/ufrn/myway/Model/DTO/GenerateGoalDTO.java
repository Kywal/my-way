package br.ufrn.myway.Model.DTO;

import java.util.List;

public record GenerateGoalDTO(
        String name,
        String description,
        Long roadmapIndex,
        List<GenerateStudyTopicDTO> studyTopics
) {
}
