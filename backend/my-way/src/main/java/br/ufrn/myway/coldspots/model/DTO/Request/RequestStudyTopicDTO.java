package br.ufrn.myway.coldspots.model.DTO.Request;

import br.ufrn.myway.coldspots.model.enums.StudyTopicStatus;

public record RequestStudyTopicDTO(
        String name,
        String description,
        StudyTopicStatus status
        ) {

}
