package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.StudyTopicDTO;
import br.ufrn.myway.Model.Entities.StudyTopic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface StudyTopicMapper {
    StudyTopic toEntity(StudyTopicDTO studyTopicDTO);
    StudyTopicDTO toDto(StudyTopic studyTopic);
    List<StudyTopicDTO> toListDTO(List<StudyTopic> studyTopicList);
}
