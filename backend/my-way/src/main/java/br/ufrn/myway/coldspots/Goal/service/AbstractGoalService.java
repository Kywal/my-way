package br.ufrn.myway.coldspots.Goal.service;

import br.ufrn.myway.coldspots.model.DTO.GoalPositionDTO;
import br.ufrn.myway.coldspots.model.DTO.Request.RequestGoalDTO;

import java.util.List;

public interface AbstractGoalService<GoalType> {

    public GoalType findById(Long id);

    public GoalType save(GoalType goal, Long roadmapId);

    public GoalType update(RequestGoalDTO goal, Long id);

    public List<GoalType> findAll();

    public void delete(Long id);

    public void changeIndexRoadmapFromGoals(Long roadmapId, List<GoalPositionDTO> goals);

    public GoalType cancelGoal(Long id);

    public GoalType finishGoal(Long id);

}
