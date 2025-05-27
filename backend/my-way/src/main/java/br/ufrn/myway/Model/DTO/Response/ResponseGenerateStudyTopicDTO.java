package br.ufrn.myway.Model.DTO.Response;

public record ResponseGenerateStudyTopicDTO(
        String name,
        String description,
        boolean isDone
) {
}
