package br.ufrn.myway.coldspots.model.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record GoalDTO(Long id,
        String type,
        String description,
        List<StudyTopicDTO> studyTopics,
        String status,
        LocalDateTime resetTime) {

}
