package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.DTO.StudyTopicDTO;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Mapper.StudyTopicMapper;
import br.ufrn.myway.Service.StudyTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/studytopic")
public class StudyTopicController {

    @Autowired
    private StudyTopicService studyTopicService;

    @Autowired
    private StudyTopicMapper studyTopicMapper;

    @PostMapping("/save")
    public ResponseEntity<StudyTopicDTO> save(@RequestBody StudyTopicDTO studyTopicDto){
        StudyTopic studyTopic = studyTopicMapper.toEntity(studyTopicDto);
        return ResponseEntity.ok(studyTopicMapper.toDto(studyTopicService.save(studyTopic)));
    }
    @GetMapping("/list")
    public List<StudyTopicDTO> list(){
        return studyTopicMapper.toListDTO(studyTopicService.list());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudyTopicDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(studyTopicMapper.toDto(studyTopicService.findById(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        studyTopicService.delete(id);
        return ResponseEntity.ok("Study Topic successfully deleted.");
    }
}
