package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/goal")
public class GoalController {
    @Autowired
    private GoalService goalService;

    @PostMapping("/create")
    public ResponseEntity<Goal> create (@RequestBody Goal newGoal){
        Goal goal = goalService.createGoal(newGoal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Goal> listGoals(){
        return goalService.listGoals();

    }
    @PostMapping("/add/{idGoal}/{idStudyTopic}")
    public ResponseEntity<String> addStudyTopicToGoal(@PathVariable Long idGoal, @PathVariable Long idStudyTopic){
        goalService.addStudyTopics(idGoal,idStudyTopic);
        return ResponseEntity.ok("Tópico adicionado com sucesso");
    }
    @GetMapping("/list/{idGoal}")
    public ResponseEntity<List<StudyTopic>> listStudyTopicsFromGoal(@PathVariable Long idGoal){
        List<StudyTopic> listOfStudyTopics = goalService.listStudyTopicsFromGoal(idGoal);
        return ResponseEntity.ok(listOfStudyTopics);
    }

}
