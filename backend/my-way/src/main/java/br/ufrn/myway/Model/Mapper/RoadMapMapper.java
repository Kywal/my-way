package br.ufrn.myway.Model.Mapper;

import java.util.List;

import br.ufrn.myway.Model.DTO.Request.RequestRoadmapDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseRoadmapDTO;
import org.mapstruct.Mapper;

import br.ufrn.myway.Model.Entities.RoadMap;

@Mapper(componentModel = "spring")

public interface RoadMapMapper {
    RoadMap toEntity(RequestRoadmapDTO roadMapDTO);
    RoadMap toEntity(ResponseRoadmapDTO roadMapDTO);

    ResponseRoadmapDTO toResponse(RoadMap roadMap);

    List<ResponseRoadmapDTO> toListDTO(List<RoadMap> list);
}

