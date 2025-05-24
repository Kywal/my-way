package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.GoalSimpleDTO;
import br.ufrn.myway.Model.DTO.Request.RequestStudyTopicDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseStudyTopicDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.StudyTopic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface StudyTopicMapper {
    StudyTopic toEntity(RequestStudyTopicDTO requestStudyTopicDTO);

    @Mapping(target = "goal", source = "goal")
    RequestStudyTopicDTO toDto(StudyTopic studyTopic);

    ResponseStudyTopicDTO toResponse(StudyTopic studyTopic);
    List<ResponseStudyTopicDTO> toResponse(List<StudyTopic> studyTopics);

    default GoalSimpleDTO goalToGoalSimpleDTO(Goal goal) {
        if (goal == null) {
            return null;
        }
        return new GoalSimpleDTO(goal.getId(), goal.getName());
    }
}
