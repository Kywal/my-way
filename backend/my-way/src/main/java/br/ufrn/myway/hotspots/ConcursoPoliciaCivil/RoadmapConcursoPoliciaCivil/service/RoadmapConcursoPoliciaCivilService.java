package br.ufrn.myway.hotspots.ConcursoPoliciaCivil.RoadmapConcursoPoliciaCivil.service;

import br.ufrn.myway.coldspots.Exceptions.BusinessException;
import br.ufrn.myway.coldspots.Roadmap.service.AbstractRoadmapService;
import br.ufrn.myway.coldspots.Roadmap.service.RoadmapBaseService;
import br.ufrn.myway.coldspots.model.entities.Goal.AbstractGoal;
import br.ufrn.myway.coldspots.model.entities.StudyTopic;
import br.ufrn.myway.coldspots.model.entities.roadmap.RoadmapBase;
import br.ufrn.myway.coldspots.model.entities.roadmap.RoadmapConcursoPoliciaCivil;
import br.ufrn.myway.coldspots.model.enums.ErrorMessageUtils;
import br.ufrn.myway.coldspots.model.enums.GoalStatus;
import br.ufrn.myway.coldspots.model.enums.RoadmapStatus;
import br.ufrn.myway.coldspots.model.enums.StudyTopicStatus;
import br.ufrn.myway.hotspots.ConcursoPoliciaCivil.RoadmapConcursoPoliciaCivil.repository.RoadmapConcursoPoliciaCivilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("Policia-civil")
@Service
public class RoadmapConcursoPoliciaCivilService extends RoadmapBaseService implements AbstractRoadmapService<RoadmapConcursoPoliciaCivil> {

    @Autowired
    private RoadmapConcursoPoliciaCivilRepository roadmapRepository;

    /**
     * Saves a RoadmapConcursoPoliciaCivil entity for the specified user.
     * Sets the user ID and status to ACTIVE before saving.
     *
     * @param roadmapConcursoPoliciaCivil the roadmap entity to save
     * @param userId the ID of the user associated with the roadmap
     * @return the saved RoadmapConcursoPoliciaCivil entity
     */
    @Override
    public RoadmapConcursoPoliciaCivil save(RoadmapConcursoPoliciaCivil roadmapConcursoPoliciaCivil, Long userId) {
        roadmapConcursoPoliciaCivil.setId(userId);
        roadmapConcursoPoliciaCivil.setStatus(RoadmapStatus.ACTIVE);
        return roadmapRepository.save(roadmapConcursoPoliciaCivil);
    }

    /**
     * Finds a `RoadmapConcursoPoliciaCivil` entity by the given user ID.
     *
     * @param userId the ID of the user whose roadmap is to be retrieved
     * @return the found `RoadmapConcursoPoliciaCivil` entity
     * @throws BusinessException if no roadmap is found for the given user ID
     */
    @Override
    public RoadmapConcursoPoliciaCivil findById(Long userId) {
        return roadmapRepository.findById(userId).orElseThrow(
            () -> new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"))
        );
    }

    /**
     * Retrieves all `RoadmapConcursoPoliciaCivil` entities.
     *
     * @return a list of all `RoadmapConcursoPoliciaCivil` entities
     */
    @Override
    public List<RoadmapConcursoPoliciaCivil> findAll() {
        return roadmapRepository.findAll();
    }


    /**
     * Retrieves all `RoadmapConcursoPoliciaCivil` entities associated with a specific user.
     *
     * @param userId the ID of the user whose roadmaps are to be retrieved
     * @return a list of `RoadmapConcursoPoliciaCivil` entities for the given user
     */
    @Override
    public List<RoadmapConcursoPoliciaCivil> findRoadmapByUser(Long userId) {
        return roadmapRepository.findRoadmapByUser(userId);
    }

    /**
     * Cancels the roadmap with the given ID by setting its status to CANCELLED.
     * Saves the updated roadmap entity.
     *
     * @param roadmapId the ID of the roadmap to cancel
     * @return the updated `RoadmapConcursoPoliciaCivil` entity with status CANCELLED
     */
    @Override
    public RoadmapConcursoPoliciaCivil cancelRoadmap(Long roadmapId) {
        RoadmapConcursoPoliciaCivil roadmap = findById(roadmapId);
        roadmap.setStatus(RoadmapStatus.CANCELLED);

        return save(roadmap, roadmap.getUser().getId());
    }

    /**
     * Marks the roadmap as finished by setting its status to CONCLUDED and saving the entity.
     *
     * @param id the ID of the roadmap to finish
     * @return the updated RoadmapConcursoPoliciaCivil entity with status CONCLUDED
     */
    @Override
    public RoadmapConcursoPoliciaCivil finishRoadmap(Long id) {
        RoadmapConcursoPoliciaCivil roadmap = findById(id);
        roadmap.setStatus(RoadmapStatus.CONCLUDED);

        return save(roadmap, roadmap.getUser().getId());
    }

    /**
     * Retrieves a `RoadmapConcursoPoliciaCivil` entity for a specific user with the given status.
     *
     * @param userId the ID of the user whose roadmap is to be retrieved
     * @param status the status of the roadmap to filter by
     * @return the found `RoadmapConcursoPoliciaCivil` entity matching the user and status
     * @throws BusinessException if no roadmap is found for the given user ID and status
     */
    @Override
    public RoadmapConcursoPoliciaCivil getByStatus(Long userId, RoadmapStatus status) {
        return roadmapRepository.findByStatus(userId, status).orElseThrow(
                () -> new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"))
        );
    }

    public String getRoadmapPreferencesPromptByUser(Long userId) {
        List<RoadmapConcursoPoliciaCivil> roadmaps = findRoadmapByUser(userId);
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
