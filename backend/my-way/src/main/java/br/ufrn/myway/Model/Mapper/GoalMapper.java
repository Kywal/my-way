package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.Request.RequestGoalDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseGoalDTO;
import br.ufrn.myway.Model.DTO.RoadMapSimpleDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.Roadmap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface GoalMapper {
    Goal toEntity(RequestGoalDTO requestGoalDTO);

    @Mapping(target = "roadMap", expression = "java(roadMapToSimple(goal.getRoadMap()))")
    RequestGoalDTO toRequest(Goal goal);

    ResponseGoalDTO toResponse(Goal goal);

    List<ResponseGoalDTO> toResponse(List<Goal> goals);

    default RoadMapSimpleDTO roadMapToSimple(Roadmap roadMap) {
        if (roadMap == null) {
            return null;
        }
        return new RoadMapSimpleDTO(roadMap.getId(), roadMap.getMainGoal(), roadMap.getStatus());
    }
}
