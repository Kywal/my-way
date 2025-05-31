package br.ufrn.myway.Service.GoalService;

import br.ufrn.myway.Model.DTO.GoalPositionDTO;
import br.ufrn.myway.Model.DTO.Request.RequestGoalDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.Roadmap;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Model.Enums.GoalStatus;
import br.ufrn.myway.Repository.GoalRepository;
import br.ufrn.myway.Service.BusinessException;
import br.ufrn.myway.Service.RoadmapService.RoadmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private RoadmapService roadMapService;

    public Goal findById(Long id) {
        Goal goal = goalRepository.getById(id);
        if (goal == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Goal"));
        }
        return goal;
    }

    public Goal save(Goal goal, Long id) {
        Roadmap roadMap = roadMapService.findById(id);
        Long lastIndex = -1L;
        goal.setRoadmap(roadMap);

        if (roadMap == null) {
            lastIndex = null;
        } else if (!roadMap.getGoals().isEmpty()) {
            lastIndex = roadMap.getGoals().getLast().getRoadmapIndex();
            goal.setRoadmapIndex(lastIndex + 1);
        } else {
            goal.setRoadmapIndex(0L);
        }

        if (goal.getStatus() == null) {
            goal.setStatus(GoalStatus.ACTIVE);
        }

        return goalRepository.save(goal);
    }

    public Goal update(RequestGoalDTO requestGoalDTO, Long id) {
        Goal oldGoal = findById(id);

        oldGoal.setName(requestGoalDTO.name());
        oldGoal.setDescription(requestGoalDTO.description());
        oldGoal.setRoadmapIndex(requestGoalDTO.roadmapIndex());
        oldGoal.setStatus(requestGoalDTO.status());
        return goalRepository.save(oldGoal);
    }

    public List<Goal> listGoals() {
        return goalRepository.list();
    }

    public void delete(Long id) {
        goalRepository.delete(id);
    }

    public void changeIndexRoadMapFromGoals(Long id, List<GoalPositionDTO> list) {
        for (GoalPositionDTO g : list) {
            Goal goal = findById(g.id());
            goal.setRoadmapIndex(g.updatedPosition());
            save(goal, id);
        }
    }

    public Goal cancelGoal(Long id) {
        Goal goal = findById(id);
        goal.setStatus(GoalStatus.CANCELLED);
        return save(goal, goal.getRoadmap().getId());
    }

    public Goal finishGoal(Long id) {
        Goal goal = findById(id);
        goal.setStatus(GoalStatus.CONCLUDED);
        return save(goal, goal.getRoadmap().getId());
    }

}
