package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.GoalDTO;
import br.ufrn.myway.Model.Entities.Goal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface GoalMapper {
    Goal toEntity(GoalDTO goalDTO);
    GoalDTO toDto(Goal goal);

}
