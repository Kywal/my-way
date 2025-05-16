package br.ufrn.myway.Model.DTO;

import br.ufrn.myway.Model.Entities.Goal;

import java.util.List;

public record RoadMapDTO (Long idRoadMap, String tittleRoadMap, String descriptionRoadMap,
                         String userAssociatedWithRoadMap, List<Goal> listGoals){
}
