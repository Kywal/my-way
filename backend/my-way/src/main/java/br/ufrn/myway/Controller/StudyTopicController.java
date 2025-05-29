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

import br.ufrn.myway.Model.DTO.Request.RequestStudyTopicDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseStudyTopicDTO;
import br.ufrn.myway.Model.Mapper.StudyTopicMapper;
import br.ufrn.myway.Service.StudyTopicService;

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

    @PostMapping("/finish/{studyTopicId}")
    public ResponseEntity<ResponseStudyTopicDTO> finish(@PathVariable Long studyTopicId) {
        return ResponseEntity.ok(studyTopicMapper.toResponse(studyTopicService.finishStudyTopic(studyTopicId)));
    }

    @PostMapping("/cancel/{studyTopicId}")
    public ResponseEntity<ResponseStudyTopicDTO> cancel(@PathVariable Long studyTopicId) {
        return ResponseEntity.ok(studyTopicMapper.toResponse(studyTopicService.cancelStudyTopic(studyTopicId)));
    }
}
