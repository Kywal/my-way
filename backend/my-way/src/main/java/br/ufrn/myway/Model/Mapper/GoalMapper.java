package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.Request.RequestGoalDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseGenerateGoalDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseGoalDTO;
import br.ufrn.myway.Model.Entities.Goal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface GoalMapper {
    Goal toEntity(RequestGoalDTO requestGoalDTO);
    Goal toEntity(ResponseGenerateGoalDTO responseGenerateGoalDTO);

    @Mapping(target = "roadmapId", source = "goal.roadmap.id")
    ResponseGoalDTO toResponse(Goal goal);

    List<ResponseGoalDTO> toResponse(List<Goal> goals);
}
