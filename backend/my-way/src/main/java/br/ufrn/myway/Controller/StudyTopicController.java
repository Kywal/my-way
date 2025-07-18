package br.ufrn.myway.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.myway.model.DTO.Request.RequestStudyTopicDTO;
import br.ufrn.myway.model.DTO.Response.ResponseStudyTopicDTO;
import br.ufrn.myway.model.entities.StudyTopic;
import br.ufrn.myway.model.mapper.StudyTopicMapper;
import br.ufrn.myway.Service.StudyTopicService;

@RestController
@RequestMapping("/studytopic")
public class StudyTopicController {

    @Autowired
    private StudyTopicService studyTopicService;

    @Autowired
    private StudyTopicMapper studyTopicMapper;

    @PostMapping("/{goalId}")
    public ResponseEntity<ResponseStudyTopicDTO> save(@RequestBody RequestStudyTopicDTO studyTopicDTO, @PathVariable Long goalId) {
        return ResponseEntity.ok(
                studyTopicMapper.toResponse(
                        studyTopicService.save(
                                studyTopicMapper.toEntity(studyTopicDTO), goalId)
                )
        );
    }

    @GetMapping("/list")
    public List<ResponseStudyTopicDTO> list(){
        return studyTopicService.list().stream()
                .map(
                        studyTopic -> studyTopicMapper.toResponse(studyTopic)
                )
                .toList();
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

    @PostMapping("/finish/{studyTopicId}")
    public ResponseEntity<ResponseStudyTopicDTO> finish(@PathVariable Long studyTopicId) {
        return ResponseEntity.ok(studyTopicMapper.toResponse(studyTopicService.finishStudyTopic(studyTopicId)));
    }

    @PostMapping("/cancel/{studyTopicId}")
    public ResponseEntity<ResponseStudyTopicDTO> cancel(@PathVariable Long studyTopicId) {
        return ResponseEntity.ok(studyTopicMapper.toResponse(studyTopicService.cancelStudyTopic(studyTopicId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyTopic> update(
            @PathVariable Long id,
            @RequestBody RequestStudyTopicDTO requestStudyTopicDTO){
        StudyTopic updated = studyTopicService.update(requestStudyTopicDTO,id);
        return ResponseEntity.ok(updated);
    }
}
