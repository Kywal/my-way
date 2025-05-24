package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.GoalSimpleDTO;
import br.ufrn.myway.Model.DTO.StudyTopicDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.StudyTopic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface StudyTopicMapper {
    StudyTopic toEntity(StudyTopicDTO studyTopicDTO);

    @Mapping(target = "goal", source = "goal")
    StudyTopicDTO toDto(StudyTopic studyTopic);
    List<StudyTopicDTO> toListDTO(List<StudyTopic> studyTopicList);

    default GoalSimpleDTO goalToGoalSimpleDTO(Goal goal) {
        if (goal == null) {
            return null;
        }
        return new GoalSimpleDTO(goal.getId(), goal.getName());
    }
}
