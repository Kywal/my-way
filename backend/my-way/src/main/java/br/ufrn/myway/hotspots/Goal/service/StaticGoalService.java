package br.ufrn.myway.hotspots.Goal.service;

import java.util.List;

import br.ufrn.myway.hotspots.ConcursoGeneralista.RoadmapConcursoGeneralista.service.RoadmapConcursoGeneralistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.ufrn.myway.coldspots.model.DTO.GoalPositionDTO;
import br.ufrn.myway.coldspots.model.DTO.Request.RequestGoalDTO;
import br.ufrn.myway.coldspots.model.entities.Goal.GoalBase;
import br.ufrn.myway.coldspots.model.entities.roadmap.RoadmapBase;
import br.ufrn.myway.coldspots.model.enums.ErrorMessageUtils;
import br.ufrn.myway.coldspots.model.enums.GoalStatus;
import br.ufrn.myway.hotspots.Goal.repository.GoalRepository;
import br.ufrn.myway.coldspots.Exceptions.BusinessException;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private RoadmapConcursoGeneralistaService roadmapConcursoGeneralistaService;

    public GoalBase findById(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
                ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Goal")));
    }

    public GoalBase save(GoalBase goal, Long roadmapId) {
        RoadmapBase roadmap = roadmapConcursoGeneralistaService.findById(roadmapId);
        goal.setRoadmap(roadmap);

        if (roadmap != null) {
            if (!roadmap.getGoals().isEmpty()) {
                Long lastIndex = roadmap.getGoals().getLast().getRoadmapIndex();
                goal.setRoadmapIndex(lastIndex + 1);
            } else {
                goal.setRoadmapIndex(0L);
            }
        }

        if (goal.getStatus() == null) {
            goal.setStatus(GoalStatus.ACTIVE);
        }

        return goalRepository.save(goal);
    }

    public GoalBase update(RequestGoalDTO requestGoalDTO, Long id) {
        GoalBase oldGoal = findById(id);

        oldGoal.setName(requestGoalDTO.name());
        oldGoal.setDescription(requestGoalDTO.description());
        oldGoal.setRoadmapIndex(requestGoalDTO.roadmapIndex());
        oldGoal.setStatus(requestGoalDTO.status());
        return goalRepository.save(oldGoal);
    }

    public List<GoalBase> listGoals() {
        return goalRepository.findAll();
    }

    public void delete(Long id) {
        goalRepository.deleteById(id);
    }

    public void changeIndexRoadMapFromGoals(Long roadmapId, List<GoalPositionDTO> list) {
        for (GoalPositionDTO g : list) {
            GoalBase goal = findById(g.id());
            goal.setRoadmapIndex(g.updatedPosition());
            save(goal, roadmapId);
        }
    }

    public GoalBase cancelGoal(Long id) {
        GoalBase goal = findById(id);
        goal.setStatus(GoalStatus.CANCELLED);
        return save(goal, goal.getRoadmap().getId());
    }

    public GoalBase finishGoal(Long id) {
        GoalBase goal = findById(id);
        goal.setStatus(GoalStatus.CONCLUDED);
        return goalRepository.save(goal);
    }

}
