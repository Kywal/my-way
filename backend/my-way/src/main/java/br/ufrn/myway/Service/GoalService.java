package br.ufrn.myway.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.Roadmap;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.GoalRepository;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepository;
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

        return goalRepository.save(goal);
    }

    public List<Goal> listGoals() {
        return goalRepository.list();
    }

    public void delete(Long id) {
        goalRepository.delete(id);
    }

}
