package br.ufrn.myway.model.DTO.Response;

import java.util.List;

public record ResponseGenerateGoalDTO(
        String name,
        String description,
        Long roadmapIndex,
        List<ResponseGenerateStudyTopicDTO> studyTopics,
        String tipo //pode ser daily ou normal
) {
}
