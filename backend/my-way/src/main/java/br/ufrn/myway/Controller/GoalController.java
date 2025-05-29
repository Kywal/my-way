package br.ufrn.myway.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.myway.Model.DTO.GoalPositionDTO;
import br.ufrn.myway.Model.DTO.Request.RequestGoalDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseGoalDTO;
import br.ufrn.myway.Model.Mapper.GoalMapper;
import br.ufrn.myway.Service.GoalService;

@RestController
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @Autowired
    private GoalMapper goalMapper;

    @PostMapping("/{roadmapId}")
    public ResponseEntity<ResponseGoalDTO> save(@RequestBody RequestGoalDTO goalDTO, @PathVariable Long roadmapId) {
        return ResponseEntity.ok(
                goalMapper.toResponse(
                        goalService.save(
                                goalMapper.toEntity(goalDTO), roadmapId
                        )
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGoalDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(goalMapper.toResponse(goalService.findById(id)));
    }

    @GetMapping("/list")
    public List<ResponseGoalDTO> listGoals() {
        return goalMapper.toResponse(goalService.listGoals());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        goalService.delete(id);
        return ResponseEntity.ok("Goal successfully deleted.");
    }

    @PostMapping("/change-goal-index/{id}")
    public ResponseEntity<String> changeIndexRoadMapFromGoals(@PathVariable Long id, @RequestBody List<GoalPositionDTO> list) {
        goalService.changeIndexRoadMapFromGoals(id, list);
        return ResponseEntity.ok("Roadmap successfully updated.");
    }

    @PostMapping("/finish-goal/{goalId}")
    public ResponseEntity<ResponseGoalDTO> finish(@PathVariable Long goalId) {
        return ResponseEntity.ok(goalMapper.toResponse(goalService.finishGoal(goalId)));
    }

    @PostMapping("/cancel-goal/{goalId}")
    public ResponseEntity<ResponseGoalDTO> cancel(@PathVariable Long goalId) {
        return ResponseEntity.ok(goalMapper.toResponse(goalService.cancelGoal(goalId)));
    }

}
