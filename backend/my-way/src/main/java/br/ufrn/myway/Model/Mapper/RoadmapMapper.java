package br.ufrn.myway.Model.Mapper;

import java.util.List;

import br.ufrn.myway.Model.DTO.Request.RequestFullRoadmapDTO;
import br.ufrn.myway.Model.DTO.Request.RequestRoadmapDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseGenerateRoadmapDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseRoadmapDTO;
import br.ufrn.myway.Model.Entities.Roadmap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoadmapMapper {
    Roadmap toEntity(RequestRoadmapDTO roadMapDTO);
    Roadmap toEntity(ResponseRoadmapDTO roadMapDTO);
    Roadmap toEntity(RequestFullRoadmapDTO roadmapDTO);
    Roadmap toEntity(ResponseGenerateRoadmapDTO generatedRoadmap);

    ResponseRoadmapDTO toResponse(Roadmap roadMap);

    List<ResponseRoadmapDTO> toListDTO(List<Roadmap> list);
}

