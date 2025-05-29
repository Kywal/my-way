package br.ufrn.myway.Controller.GoalController;

import br.ufrn.myway.Model.DTO.Response.ResponseGenerateGoalDTO;
import br.ufrn.myway.Model.Mapper.GoalMapper;
import br.ufrn.myway.Service.GoalService.AiGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/goal")
public class AiGoalController {

    @Autowired
    private AiGoalService aiGoalService;

    @Autowired
    private GoalMapper goalMapper;

    @PostMapping("/gen-and-save/{roadmapId}")
    public ResponseEntity<ResponseGenerateGoalDTO> generateAndSave(@PathVariable Long roadmapId, @RequestBody Long roadmapIndex) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                aiGoalService.generateGoal(roadmapId, roadmapIndex)
        );
    }

}