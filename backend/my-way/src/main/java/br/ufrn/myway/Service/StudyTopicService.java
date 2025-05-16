package br.ufrn.myway.Service;

import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Repository.StudyTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudyTopicService {
    @Autowired
    StudyTopicRepository studyTopicRepository;

    public StudyTopicService(StudyTopicRepository studyTopicRepository) {
        this.studyTopicRepository = studyTopicRepository;
    }

    public StudyTopic searchStudyTopic(Long idStudyTopic){
        return studyTopicRepository.findById(idStudyTopic).orElseThrow(()-> new RuntimeException("not found"));
    }

    public StudyTopic createStudyTopic(StudyTopic newStudyTopic){
        return studyTopicRepository.save(newStudyTopic);
    }
    public List<StudyTopic> listStudyTopic(){
        return studyTopicRepository.findAll();
    }

}
