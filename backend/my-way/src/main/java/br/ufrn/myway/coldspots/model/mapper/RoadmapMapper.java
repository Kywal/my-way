package br.ufrn.myway.coldspots.model.mapper;

import br.ufrn.myway.coldspots.model.DTO.Request.RequestFullRoadmapDTO;
import br.ufrn.myway.coldspots.model.DTO.Request.RequestRoadmapDTO;
import br.ufrn.myway.coldspots.model.DTO.Response.ResponseRoadmapDTO;
import br.ufrn.myway.coldspots.model.entities.roadmap.RoadmapConcursoGeneralista;
import br.ufrn.myway.coldspots.model.entities.roadmap.RoadmapConcursoPoliciaCivil;
import br.ufrn.myway.coldspots.model.entities.roadmap.RoadmapConcursoProfessor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class RoadmapMapper {

    @Autowired
    protected GoalMapper goalMapper;

    @Autowired
    protected ConcursoMapper concursoMapper;

    public abstract RoadmapConcursoPoliciaCivil toRoadmapConcursoPoliciaCivilEntity(RequestRoadmapDTO request);
    public abstract RoadmapConcursoPoliciaCivil toRoadmapConcursoPoliciaCivilEntity(RequestFullRoadmapDTO request);
    public abstract RoadmapConcursoGeneralista toRoadmapConcursoGeneralistaEntity(RequestRoadmapDTO roadmapDTO);
    public abstract RoadmapConcursoGeneralista toRoadmapConcursoGeneralistaEntity(RequestFullRoadmapDTO roadmapDTO);

    public abstract RoadmapConcursoProfessor toRoadmapConcursoProfessorEntity(RequestRoadmapDTO roadmapDTO);
    public abstract RoadmapConcursoProfessor toRoadmapConcursoProfessorEntity(RequestFullRoadmapDTO roadmapDTO);

    public abstract ResponseRoadmapDTO toResponse(RoadmapConcursoPoliciaCivil roadmap);
    public abstract ResponseRoadmapDTO toResponse(RoadmapConcursoGeneralista roadmap);
    public abstract ResponseRoadmapDTO toResponse(RoadmapConcursoProfessor roadmapConcursoProfessor);

    public abstract RoadmapConcursoProfessor toRoadmapConcursoProfessor(RequestRoadmapDTO request);
    public abstract RoadmapConcursoProfessor toRoadmapConcursoProfessor(RequestFullRoadmapDTO request);

    public abstract RoadmapConcursoGeneralista toEntity(RequestRoadmapDTO roadmapDTO);
    public abstract RoadmapConcursoGeneralista toEntity(RequestFullRoadmapDTO roadmapDTO);
}
