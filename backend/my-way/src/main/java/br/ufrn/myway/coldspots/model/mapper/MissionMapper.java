package br.ufrn.myway.coldspots.model.mapper;

import org.mapstruct.Mapper;

import br.ufrn.myway.coldspots.model.DTO.MissionDTO;
import br.ufrn.myway.coldspots.Mission.model.Mission;

@Mapper(componentModel = "spring")
public interface MissionMapper {

    Mission toEntity(MissionDTO dto);

    MissionDTO toDto(Mission user);
}
