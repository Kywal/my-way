package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.DTO.Request.RequestStudyTopicDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseStudyTopicDTO;
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

    @PostMapping("/{goalId}")
    public ResponseEntity<ResponseStudyTopicDTO> save(@RequestBody RequestStudyTopicDTO requestStudyTopicDto, @PathVariable Long goalId) {
        return ResponseEntity.ok(
                studyTopicMapper.toResponse(
                        studyTopicService.save(
                                studyTopicMapper.toEntity(requestStudyTopicDto), goalId)
                )
        );
    }

    @GetMapping("/list")
    public List<ResponseStudyTopicDTO> list(){
        return studyTopicMapper.toResponse(studyTopicService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStudyTopicDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(
                studyTopicMapper.toResponse(
                        studyTopicService.findById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        studyTopicService.delete(id);
        return ResponseEntity.ok("Study Topic successfully deleted.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudyTopic> update(
            @PathVariable Long id,
            @RequestBody RequestStudyTopicDTO requestStudyTopicDTO){
        StudyTopic updated = studyTopicService.update(requestStudyTopicDTO,id);
        return ResponseEntity.ok(updated);
    }
}
