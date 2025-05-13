package br.ufrn.myway.Model.Mapper;

import org.mapstruct.Mapper;

import br.ufrn.myway.Model.DTO.MissionDTO; 
import br.ufrn.myway.Model.Entities.Mission;

@Mapper(componentModel = "spring")
public interface MissionMapper {

    Mission toEntity(MissionDTO dto);

    MissionDTO toDto(Mission user);
}
