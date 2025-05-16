package br.ufrn.myway.Service;

import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Repository.GoalRepository;
import br.ufrn.myway.Repository.StudyTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {


    @Autowired
    GoalRepository goalRepository;

    @Autowired
    StudyTopicRepository studyTopicRepository;

    public GoalService(GoalRepository goalRepository, StudyTopicRepository studyTopicRepository) {
        this.goalRepository = goalRepository;
        this.studyTopicRepository = studyTopicRepository;
    }

    public Goal searchGoal(Long idGoal){
        return goalRepository.findById(idGoal).orElseThrow(()-> new RuntimeException("not found"));
    }

    public Goal createGoal(Goal newGoal){
        return goalRepository.save(newGoal);
    }
    public List<Goal> listGoals(){
        return goalRepository.findAll();
    }

    public List<StudyTopic> listStudyTopicsFromGoal(Long idGoal) {
        Goal goal = searchGoal(idGoal);
        return goal.getListTopics();
    }
    public void addStudyTopics(Long idGoal, Long idStudyTopics){
        Goal goal = searchGoal(idGoal);

        StudyTopic topic = studyTopicRepository.findById(idStudyTopics)
                .orElseThrow(() -> new RuntimeException("StudyTopic não encontrado"));

        goal.getListTopics().add(topic);
        topic.setGoal(goal);

        goalRepository.save(goal);
    }
}
