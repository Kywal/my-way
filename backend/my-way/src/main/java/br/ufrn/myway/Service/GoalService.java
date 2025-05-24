package br.ufrn.myway.Service;

import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.Roadmap;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepository;
    @Autowired
    private RoadmapService roadMapService;

    public Goal findById(Long id) {
        Goal goal = goalRepository.getById(id);
        if (goal == null) {
            throw new BusinessException(ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Goal"));
        }
        return goal;
    }
    public Goal save(Goal goal, Long id){
        Roadmap roadMap = roadMapService.findById(id);
        goal.setRoadMap(roadMap);
        return goalRepository.save(goal);
    }

    public List<Goal> listGoals(){
        return goalRepository.list();
    }

    public void delete(Long id){
        goalRepository.delete(id);
    }

}
