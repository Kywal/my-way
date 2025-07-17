package br.ufrn.myway.Model.DTO.Response;

import java.util.List;

public record ResponseGenerateGoalDTO(
        String name,
        String description,
        Long roadmapIndex,
        List<ResponseGenerateStudyTopicDTO> studyTopics,
        String tipo //pode ser daily ou normal
) {
}
