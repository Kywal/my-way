package br.ufrn.myway.Service;

import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.RoadMap;
import br.ufrn.myway.Repository.GoalRepository;
import br.ufrn.myway.Repository.RoadMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoadMapService {
    @Autowired
    private RoadMapRepository roadMapRepository;

    @Autowired
    private GoalRepository goalRepository;

    public RoadMapService(RoadMapRepository roadMapRepository, GoalRepository goalRepository) {
        this.roadMapRepository = roadMapRepository;
        this.goalRepository = goalRepository;
    }

    public RoadMap searchRoadMap(Long idRoadMap){
        return roadMapRepository.findById(idRoadMap).orElseThrow(()-> new RuntimeException("not found"));
    }

    public RoadMap createRoadMap(RoadMap newRoadMap){
        return roadMapRepository.save(newRoadMap);
    }
    public List<RoadMap> listRoadMap(){
        return roadMapRepository.findAll();
    }

    public void addGoalsToRoadMap(Long idRoadMap, Long idGoal){
        RoadMap roadMap = searchRoadMap(idRoadMap);

        Goal goal = goalRepository.findById(idGoal)
                .orElseThrow(() -> new RuntimeException("Goal não encontrado"));

        roadMap.getListGoals().add(goal);
        goal.setRoadMap(roadMap);

        roadMapRepository.save(roadMap);
    }

    public List<Goal> listGoalsFromRoadMap(Long idRoadMap) {
        RoadMap roadMap = searchRoadMap(idRoadMap);
        return roadMap.getListGoals();
    }
}
