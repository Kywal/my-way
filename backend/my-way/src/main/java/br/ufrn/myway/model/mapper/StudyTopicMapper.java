package br.ufrn.myway.model.mapper;

import br.ufrn.myway.model.DTO.Request.RequestStudyTopicDTO;
import br.ufrn.myway.model.DTO.Response.ResponseStudyTopicDTO;
import org.mapstruct.Mapper;

import br.ufrn.myway.model.DTO.StudyTopicDTO;
import br.ufrn.myway.model.entities.StudyTopic;

@Mapper(componentModel = "spring")
public abstract class StudyTopicMapper {


    public abstract StudyTopic toEntity(StudyTopicDTO dto);
    public abstract StudyTopic toEntity(RequestStudyTopicDTO dto);

    public abstract StudyTopicDTO toDTO(StudyTopic entity);
    public abstract ResponseStudyTopicDTO toResponse(StudyTopic entity);

}
