package br.ufrn.myway.Model.Mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.ufrn.myway.Model.DTO.RoadMapDTO;
import br.ufrn.myway.Model.Entities.RoadMap;

@Mapper(componentModel = "spring")

public interface RoadMapMapper {
    RoadMap toEntity(RoadMapDTO roadMapDTO);
    RoadMapDTO toDto(RoadMap roadMap);

    List<RoadMapDTO> toListDTO(List<RoadMap> list);
}

