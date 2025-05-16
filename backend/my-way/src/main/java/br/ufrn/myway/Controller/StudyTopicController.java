package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Service.StudyTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/studytopic")
public class StudyTopicController {
    @Autowired
    private StudyTopicService studyTopicService;

    @PostMapping("/create")
    public ResponseEntity<StudyTopic> create(@RequestBody StudyTopic newStudyTopic){
        StudyTopic studyTopic = studyTopicService.createStudyTopic(newStudyTopic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public List<StudyTopic> listStudyTopics(){
        return studyTopicService.listStudyTopic();
    }
}
