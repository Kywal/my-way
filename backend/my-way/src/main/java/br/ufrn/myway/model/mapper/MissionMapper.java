package br.ufrn.myway.model.mapper;

import org.mapstruct.Mapper;

import br.ufrn.myway.model.DTO.MissionDTO;
import br.ufrn.myway.model.entities.Mission;

@Mapper(componentModel = "spring")
public interface MissionMapper {

    Mission toEntity(MissionDTO dto);

    MissionDTO toDto(Mission user);
}
