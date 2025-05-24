package br.ufrn.myway.Model.DTO.Request;

public record RequestStudyTopicDTO(
        String name,
        String description,
        Long goalId,
        boolean isDone
) {
}
