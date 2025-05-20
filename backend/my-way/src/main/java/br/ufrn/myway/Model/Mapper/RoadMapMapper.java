package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.RoadMapDTO;
import br.ufrn.myway.Model.Entities.RoadMap;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface RoadMapMapper {
    RoadMap toEntity(RoadMapDTO roadMapDTO);
    RoadMapDTO toDto(RoadMap roadMap);

    List<RoadMapDTO> toListDTO(List<RoadMap> list);
}
