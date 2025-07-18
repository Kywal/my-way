package br.ufrn.myway.Service.RoadmapService;

import br.ufrn.myway.Model.Entities.Goal.AbstractGoal;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapConcursoGeneralista;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Model.Enums.GoalStatus;
import br.ufrn.myway.Model.Enums.RoadmapStatus;
import br.ufrn.myway.Model.Enums.StudyTopicStatus;
import br.ufrn.myway.Repository.Roadmap.RoadmapConcursoGeneralistaRepository;
import br.ufrn.myway.Service.BusinessException;
import br.ufrn.myway.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapConcursoGeneralistaService extends RoadmapBaseService implements AbstractRoadmapService<RoadmapConcursoGeneralista> {

    @Autowired
    private RoadmapConcursoGeneralistaRepository roadmapRepository;

    @Autowired
    private UserService userService;

    @Override
    public RoadmapConcursoGeneralista save(RoadmapConcursoGeneralista roadmap, Long id) {
        User user = userService.findById(id);
        if (roadmap.getUser() == null) {
            roadmap.setUser(user);
        }
        if (roadmap.getStatus() == null) {
            roadmap.setStatus(RoadmapStatus.ACTIVE);
        }

        return roadmapRepository.save(roadmap);
    }

    @Override
    public RoadmapConcursoGeneralista findById(Long userId) {
        return roadmapRepository.findById(userId).orElseThrow(
                () -> new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap")
        ));
    }

    @Override
    public List<RoadmapConcursoGeneralista> findAll() {
        return roadmapRepository.list();
    }

    @Override
    public List<RoadmapConcursoGeneralista> findRoadmapByUser(Long userId) {
        return roadmapRepository.findRoadmapByUser(userId);
    }

    @Override
    public RoadmapConcursoGeneralista cancelRoadmap(Long roadmapId) {
        RoadmapConcursoGeneralista roadmap = findById(roadmapId);
        roadmap.setStatus(RoadmapStatus.CANCELLED);
        return save(roadmap, roadmap.getUser().getId());
    }

    @Override
    public RoadmapConcursoGeneralista finishRoadmap(Long id) {
        RoadmapConcursoGeneralista roadmap = findById(id);
        roadmap.setStatus(RoadmapStatus.CONCLUDED);
        return save(roadmap, roadmap.getUser().getId());
    }

    @Override
    public RoadmapConcursoGeneralista getByStatus(Long userId, RoadmapStatus status) {
        return roadmapRepository.findByStatus(userId, status).orElseThrow(
            () -> new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"))
        );
    }

    public String getRoadmapPreferencesPromptByUser(Long userId) {
        List<RoadmapConcursoGeneralista> roadmaps = findRoadmapByUser(userId);
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
