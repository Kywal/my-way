package br.ufrn.myway.Model.DTO;

import br.ufrn.myway.Model.Entities.StudyTopic;

import java.util.List;

public record GoalDTO(Long idGoal, String nameGoal, List<StudyTopic> listTopics) {
}
