package br.ufrn.myway.Service;

import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.StudyTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudyTopicService {
    @Autowired
    StudyTopicRepository studyTopicRepository;

    public StudyTopic findById(Long id){
        StudyTopic studyTopic = studyTopicRepository.getById(id);
        if(studyTopic == null){
            throw new BusinessException(ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Study Topic"));
        }
        return studyTopic;
    }
    public StudyTopic save(StudyTopic studyTopic){
        return studyTopicRepository.save(studyTopic);
    }

    public List<StudyTopic> list(){
        return studyTopicRepository.list();
    }

    public void delete(Long id){
        studyTopicRepository.delete(id);
    }

}
