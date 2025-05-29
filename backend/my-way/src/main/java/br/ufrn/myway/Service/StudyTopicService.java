package br.ufrn.myway.Service;

import br.ufrn.myway.Model.DTO.Request.RequestStudyTopicDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.StudyTopicRepository;
import br.ufrn.myway.Service.GoalService.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudyTopicService {
    @Autowired
    StudyTopicRepository studyTopicRepository;
    @Autowired
    private GoalService goalService;

    public StudyTopic findById(Long id){
        StudyTopic studyTopic = studyTopicRepository.getById(id);
        if(studyTopic == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Study Topic"));
        }
        return studyTopic;
    }
    public StudyTopic save(StudyTopic studyTopic, Long id){
        Goal goal = goalService.findById(id);
        studyTopic.setGoal(goal);
        return studyTopicRepository.save(studyTopic);
    }


    public StudyTopic update(RequestStudyTopicDTO studyTopicUpdated, long id){
        StudyTopic studyTopicOld = findById(id);

        studyTopicOld.setName(studyTopicUpdated.name());
        studyTopicOld.setDescription(studyTopicUpdated.description());
        studyTopicOld.setIsDone(studyTopicUpdated.isDone());

        return studyTopicRepository.save(studyTopicOld);
    }
    public List<StudyTopic> list(){
        return studyTopicRepository.list();
    }

    public void delete(Long id){
        studyTopicRepository.delete(id);
    }

}
