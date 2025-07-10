package br.ufrn.myway.Model.Mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.ufrn.myway.Model.DTO.StudyTopicDTO;
import br.ufrn.myway.Model.Entities.StudyTopic;

@Mapper(componentModel = "spring")
public interface StudyTopicMapper {

    default StudyTopic toEntity(StudyTopicDTO dto) {
        StudyTopic entity = new StudyTopic();
        entity.setId(dto.id());
        entity.setName(dto.name());
        return entity;
    }

    default StudyTopicDTO toDTO(StudyTopic entity) {
        return new StudyTopicDTO(entity.getId(), entity.getName());
    }

    List<StudyTopicDTO> toDTOList(List<StudyTopic> list);

    List<StudyTopic> toEntityList(List<StudyTopicDTO> list);
}
