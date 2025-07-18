package br.ufrn.myway.Service.RoadmapService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Model.Entities.Goal.AbstractGoal; 
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Model.Enums.GoalStatus;
import br.ufrn.myway.Model.Enums.RoadmapStatus;
import br.ufrn.myway.Model.Enums.StudyTopicStatus;
import br.ufrn.myway.Repository.RoadmapRepository;
import br.ufrn.myway.Service.BusinessException;
import br.ufrn.myway.Service.UserService;

@Service
public class RoadmapService {

    @Autowired
    private RoadmapRepository roadmapRepository;

    @Autowired
    private UserService userService;

    public RoadmapBase findById(Long id) {
        RoadmapBase roadMap = roadmapRepository.getById(id);
        if (roadMap == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"));
        }
        return roadMap;
    }

    public RoadmapBase save(RoadmapBase roadmap, Long id) {
        User user = userService.findById(id);
        if (roadmap.getUser() == null) {
            roadmap.setUser(user);
        }
        if (roadmap.getStatus() == null) {
            roadmap.setStatus(RoadmapStatus.ACTIVE);
        }

        return roadmapRepository.save(roadmap);
    }

    public List<RoadmapBase> list() {
        return roadmapRepository.list();
    }

    public void deletar(Long id) {
        roadmapRepository.delete(id);
    }

    public List<RoadmapBase> findRoadMapByUser(Long id) {
        return roadmapRepository.findRoadMapByUser(id);
    }

    public RoadmapBase cancelRoadmap(Long id) {
        RoadmapBase roadmap = findById(id);
        roadmap.setStatus(RoadmapStatus.CANCELLED);
        return save(roadmap, roadmap.getUser().getId());
    }

    public RoadmapBase finishRoadmap(Long id) {
        RoadmapBase roadmap = findById(id);
        roadmap.setStatus(RoadmapStatus.CONCLUDED);
        return save(roadmap, roadmap.getUser().getId());
    }

    public RoadmapBase getByStatus(Long userId, RoadmapStatus status) {
        RoadmapBase roadmap = roadmapRepository.findByStatus(userId, status);
        if (roadmap == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"));
        }
        return roadmap;
    }

    public String getRoadmapPreferencesPromptByUser(Long userId) {
        List<RoadmapBase> roadmaps = findRoadMapByUser(userId); 
        if (roadmaps.isEmpty()) {
            return "";
        }

        String prompt = "Utilize as informações abaixo para gerar um roadmap personalizado para o usuário.\n\n";
        prompt = prompt.concat("Se o usuário já concluiu um roadmap/goal/studyTopic, tente não repetir assuntos relacionado a esta roadmap.\n");
        prompt = prompt.concat("Se o usuário cancelou um roadmap/goal/studyTopic, pode ser um assunto que não lhe interessa, ou que ele já saiba, ou até mesmo que ele ache avançado, avalie.\n");

        for (RoadmapBase roadmap : roadmaps) {
            if (roadmap.getStatus() == RoadmapStatus.CANCELLED) {
                prompt = prompt.concat("O usuário cancelou o roadmap de objetivo principal: " + roadmap.getMainGoal() + ".\n");
            } else if (roadmap.getStatus() == RoadmapStatus.CONCLUDED) {
                prompt = prompt.concat("O usuário concluiu o roadmap de objetivo principal: " + roadmap.getMainGoal() + ".\n");
            }

            for (AbstractGoal goal : roadmap.getGoals()) {
                if (goal.getStatus() == GoalStatus.CANCELLED) {
                    prompt = prompt.concat("O usuário cancelou o objetivo(goal): " + goal.getName() + ".\n");
                } else if (goal.getStatus() == GoalStatus.CONCLUDED) {
                    prompt = prompt.concat("O usuário concluiu o objetivo(goal): " + goal.getName() + ".\n");
                }
                for (StudyTopic studyTopic : goal.getStudyTopics()) {
                    if (studyTopic.getStatus() == StudyTopicStatus.CANCELLED) {
                        prompt = prompt.concat("O usuário cancelou o tópico: " + studyTopic.getName() + " relacionado ao objetivo(goal): " + goal.getName() + ".\n");
                    }
                }
            }
        }

        System.out.println("Prompt for Roadmap Preferences: " + prompt);

        return prompt;
    }
}
