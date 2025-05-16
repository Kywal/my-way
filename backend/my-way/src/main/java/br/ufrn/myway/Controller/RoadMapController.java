package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.RoadMap;
import br.ufrn.myway.Service.RoadMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roadmap")
public class RoadMapController {
    @Autowired
    RoadMapService roadMapService;

    @PostMapping("/create")
    public ResponseEntity<RoadMap> createRoadMap(@RequestBody RoadMap newRoadMap){
        RoadMap roadMap = roadMapService.createRoadMap(newRoadMap);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public List<RoadMap> listAllRoadMaps(){
        return roadMapService.listRoadMap();
    }
    @PostMapping("/add/{idRoadMap}/{idGoal}")
    public ResponseEntity<String> addGoalToRoadMap(@PathVariable Long idRoadMap, @PathVariable Long idGoal){
        roadMapService.addGoalsToRoadMap(idRoadMap, idGoal);
        return ResponseEntity.ok("adicionado");
    }
    @GetMapping("/list/{idRoadMap}")
    public ResponseEntity<List<Goal>> listGoalsRoadMap(@PathVariable Long idRoadMap){
        List<Goal> listOfGoals = roadMapService.listGoalsFromRoadMap(idRoadMap);
        return ResponseEntity.ok(listOfGoals);
    }
}
