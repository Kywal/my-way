package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.GoalDTO;
import br.ufrn.myway.Model.DTO.RoadMapSimpleDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.Roadmap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface GoalMapper {
    Goal toEntity(GoalDTO goalDTO);

    @Mapping(target = "roadMap", expression = "java(roadMapToSimple(goal.getRoadMap()))")
    GoalDTO toDto(Goal goal);
    List<GoalDTO> toListDTO(List<Goal> goals);

    default RoadMapSimpleDTO roadMapToSimple(Roadmap roadMap) {
        if (roadMap == null) {
            return null;
        }
        return new RoadMapSimpleDTO(roadMap.getId(), roadMap.getName(), roadMap.getStatus());
    }
}
