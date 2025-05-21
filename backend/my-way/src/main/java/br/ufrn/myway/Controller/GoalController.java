package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.DTO.GoalDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Mapper.GoalMapper;
import br.ufrn.myway.Service.GoalService;
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

    @PostMapping("/save/{id}")
    public ResponseEntity<GoalDTO> save(@RequestBody GoalDTO goalDto, @PathVariable Long id){
        Goal goal = goalMapper.toEntity(goalDto);
        return ResponseEntity.ok(goalMapper.toDto(goalService.save(goal, id)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GoalDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(goalMapper.toDto(goalService.findById(id)));
    }

    @GetMapping("/list")
    public List<GoalDTO> listGoals(){
        return goalMapper.toListDTO(goalService.listGoals());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        goalService.delete(id);
        return ResponseEntity.ok("Goal successfully deleted.");
    }

}
