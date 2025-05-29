package br.ufrn.myway.Controller.GoalController;

import br.ufrn.myway.Model.DTO.GoalPositionDTO;
import br.ufrn.myway.Model.DTO.Request.RequestGoalDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseGoalDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Mapper.GoalMapper;
import br.ufrn.myway.Service.GoalService.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @Autowired
    private GoalMapper goalMapper;

    @PostMapping("/{roadmapId}")
    public ResponseEntity<ResponseGoalDTO> save(@RequestBody RequestGoalDTO goalDTO, @PathVariable Long roadmapId){
        return ResponseEntity.ok(
                goalMapper.toResponse(
                        goalService.save(
                                goalMapper.toEntity(goalDTO), roadmapId
                        )
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGoalDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(goalMapper.toResponse(goalService.findById(id)));
    }

    @GetMapping("/list")
    public List<ResponseGoalDTO> listGoals(){
        return goalMapper.toResponse(goalService.listGoals());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        goalService.delete(id);
        return ResponseEntity.ok("Goal successfully deleted.");
    }

    @PostMapping("/change-goal-index/{id}")
    public ResponseEntity<String> changeIndexRoadMapFromGoals(@PathVariable Long id, @RequestBody List<GoalPositionDTO> list) {
        goalService.changeIndexRoadMapFromGoals(id, list);
        return ResponseEntity.ok("Roadmap successfully updated.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Goal> update(
            @PathVariable Long id,
            @RequestBody RequestGoalDTO requestGoalDTO){
        Goal updated = goalService.update(requestGoalDTO, id);
        return ResponseEntity.ok(updated);
    }
}
