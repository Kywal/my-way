package br.ufrn.myway.Model.DTO.Request;

import br.ufrn.myway.Model.Enums.StudyTopicStatus;

public record RequestStudyTopicDTO(
        String name,
        String description,
        StudyTopicStatus status
        ) {

}
