package br.ufrn.myway.Model.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import br.ufrn.myway.Model.DTO.StudyTopicDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseGenerateStudyTopicDTO;
import br.ufrn.myway.Model.Entities.StudyTopic;

@Mapper(componentModel = "spring")
public abstract class StudyTopicMapper {

    // default StudyTopic toEntity(StudyTopicDTO dto) {
    //     StudyTopic entity = new StudyTopic();
    //     entity.setId(dto.id());
    //     entity.setName(dto.name());
    //     return entity;
    // }

    // default StudyTopicDTO toDTO(StudyTopic entity) {
    //     return new StudyTopicDTO(entity.getId(), entity.getName());
    // }

    // List<StudyTopicDTO> toDTOList(List<StudyTopic> list);

    // List<StudyTopic> toEntityList(List<StudyTopicDTO> list);

    
    public abstract StudyTopic toEntity(StudyTopicDTO dto);

    public abstract StudyTopicDTO toDTO(StudyTopic entity);

    public List<StudyTopic> toEntityList(List<StudyTopicDTO> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }

    public List<StudyTopicDTO> toDTOList(List<StudyTopic> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    // Add this method to fix the error
    public List<StudyTopicDTO> toDTOListFromResponse(List<ResponseGenerateStudyTopicDTO> responseDTOs) {
        if (responseDTOs == null) {
            return null;
        }
        return responseDTOs.stream()
            .map(responseDTO -> new StudyTopicDTO(
                1L,
                responseDTO.name()
            ))
            .collect(Collectors.toList());
    }
}
