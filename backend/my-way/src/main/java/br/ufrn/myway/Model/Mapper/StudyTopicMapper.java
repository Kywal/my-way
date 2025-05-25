package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.Request.RequestStudyTopicDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseStudyTopicDTO;
import br.ufrn.myway.Model.Entities.StudyTopic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface StudyTopicMapper {
    StudyTopic toEntity(RequestStudyTopicDTO requestStudyTopicDTO);

    RequestStudyTopicDTO toRequest(StudyTopic studyTopic);

    @Mapping(target = "goalId", source = "studyTopic.goal.id")
    ResponseStudyTopicDTO toResponse(StudyTopic studyTopic);

    List<ResponseStudyTopicDTO> toResponse(List<StudyTopic> studyTopics);
}
