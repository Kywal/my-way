package br.ufrn.myway.Model.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufrn.myway.Model.DTO.Request.RequestRoadmapDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseRoadmapDTO;
import br.ufrn.myway.Model.Entities.Concurso.AbstractConcurso;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapConcursoPoliciaCivil;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapConcursoProfessor;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapConcursoGeneralista;

@Mapper(componentModel = "spring", uses = {GoalMapper.class, ConcursoMapper.class})
public abstract class RoadmapMapper {

    @Autowired
    protected GoalMapper goalMapper;

    @Autowired
    protected ConcursoMapper concursoMapper;

    public RoadmapBase toEntity(RequestRoadmapDTO dto, AbstractConcurso concurso, String tipo) {
        RoadmapBase roadmap;

        switch (tipo.toUpperCase()) {
            case "POLICIA_CIVIL" ->
                roadmap = new RoadmapConcursoPoliciaCivil();
            case "PROFESSOR" ->
                roadmap = new RoadmapConcursoProfessor();
            default ->
                roadmap = new RoadmapConcursoGeneralista();
        }

        roadmap.setMainGoal(dto.mainGoal());
        roadmap.setGoals(goalMapper.toEntityListFromResponse(dto.goals()));
        roadmap.setConcurso(concurso);

        return roadmap;
    }

    // @Mapping(target = "id", ignore = true)
    // @Mapping(target = "mainGoal", source = "mainGoal")
    // @Mapping(target = "goals", source = "goals")
    // RoadmapBase toEntity(ResponseGenerateRoadmapDTO dto){
    //     RoadmapBase roadmap = new RoadmapConcursoGeneralista();
    //     roadmap.setMainGoal(dto.mainGoal());
    //     roadmap.setDescription(dto.description());
    //     roadmap.setGoals(goalMapper.toEntityList(dto.goals()));
    //     roadmap.setStatus(dto.status());

    //     return roadmap;
    // }

    public ResponseRoadmapDTO toDTO(RoadmapBase roadmap) {
        String type = switch (roadmap) {
            case RoadmapConcursoPoliciaCivil ignored ->
                "POLICIA_CIVIL";
            case RoadmapConcursoProfessor ignored ->
                "PROFESSOR";
            default ->
                "GENERAL";
        };

        return new ResponseRoadmapDTO(
                roadmap.getId(),
                type,
                roadmap.getCreatedAt(),
                roadmap.getMainGoal(),
                roadmap.getDescription(),
                goalMapper.toDTOList(roadmap.getGoals()),
                roadmap.getStatus()
        );
    }

    // Lista de entidades -> lista de DTOs
    public List<ResponseRoadmapDTO> toDTOList(List<RoadmapBase> roadmaps) {
        return roadmaps.stream().map(this::toDTO).toList();
    }
}
