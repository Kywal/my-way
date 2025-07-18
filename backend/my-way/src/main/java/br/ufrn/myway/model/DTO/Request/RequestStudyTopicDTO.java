package br.ufrn.myway.model.DTO.Request;

import br.ufrn.myway.model.Enums.StudyTopicStatus;

public record RequestStudyTopicDTO(
        String name,
        String description,
        StudyTopicStatus status
        ) {

}
