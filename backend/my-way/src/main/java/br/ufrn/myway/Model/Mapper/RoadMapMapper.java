package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.RoadMapDTO;
import br.ufrn.myway.Model.Entities.RoadMap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RoadMapMapper {
    RoadMap toEntity(RoadMap roadMapDTO);
    RoadMapDTO toDto(RoadMap roadMap);
}
