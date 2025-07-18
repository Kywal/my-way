package br.ufrn.myway.Service;

import java.util.List;

import br.ufrn.myway.model.DTO.Request.RequestStudyTopicDTO;
import br.ufrn.myway.model.entities.StudyTopic;
import br.ufrn.myway.model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.StudyTopicRepository;
import br.ufrn.myway.Service.GoalService.GoalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.ufrn.myway.model.entities.Goal.GoalBase;
import br.ufrn.myway.model.Enums.StudyTopicStatus;

@Service
public class StudyTopicService {

    @Autowired
    StudyTopicRepository studyTopicRepository;
    @Autowired
    private GoalService goalService;

    public StudyTopic findById(Long id) {
        StudyTopic studyTopic = studyTopicRepository.getById(id);
        if (studyTopic == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Study Topic"));
        }
        return studyTopic;
    }

    public StudyTopic save(StudyTopic studyTopic, Long id) {
        GoalBase goal = goalService.findById(id);
        studyTopic.setGoal(goal);

        if (studyTopic.getStatus() == null) {
            studyTopic.setStatus(StudyTopicStatus.ACTIVE);
        }
        return studyTopicRepository.save(studyTopic);
    }

    public StudyTopic update(RequestStudyTopicDTO studyTopicUpdated, long id) {
        StudyTopic studyTopicOld = findById(id);

        studyTopicOld.setName(studyTopicUpdated.name());
        studyTopicOld.setDescription(studyTopicUpdated.description());
        studyTopicOld.setStatus(studyTopicUpdated.status());

        return studyTopicRepository.save(studyTopicOld);
    }

    public List<StudyTopic> list() {
        return studyTopicRepository.findAll();
    }

    public void delete(Long id) {
        studyTopicRepository.delete(id);
    }

    public StudyTopic cancelStudyTopic(Long id) {
        StudyTopic studyTopic = findById(id);
        studyTopic.setStatus(StudyTopicStatus.CANCELLED);
        return save(studyTopic, studyTopic.getGoal().getId());
    }

    public StudyTopic finishStudyTopic(Long id) {
        StudyTopic studyTopic = findById(id);
        studyTopic.setStatus(StudyTopicStatus.CONCLUDED);
        return save(studyTopic, studyTopic.getGoal().getId());
    }

}
