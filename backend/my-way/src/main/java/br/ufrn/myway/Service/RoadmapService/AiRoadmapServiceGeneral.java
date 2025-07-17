package br.ufrn.myway.Service.RoadmapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Model.DTO.Request.RequestRoadmapDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseGenerateRoadmapDTO;
import br.ufrn.myway.Model.Entities.Concurso.AbstractConcurso;
import br.ufrn.myway.Model.Entities.Concurso.GeneralConcurso;
import br.ufrn.myway.Model.Entities.Goal.AbstractGoal;
import br.ufrn.myway.Model.Entities.Goal.GoalBase;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapGeneralConcurso;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.GoalStatus;
import br.ufrn.myway.Model.Mapper.RoadmapMapper;
import br.ufrn.myway.Service.GoalService.GoalService;
import br.ufrn.myway.Service.StudyTopicService;
import br.ufrn.myway.Service.UserService;

@Service
public class AiRoadmapServiceGeneral extends AiRoadmapService {

    @Autowired
    private RoadmapService roadmapService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private StudyTopicService studyTopicService;

    @Autowired
    private RoadmapMapper roadmapMapper;

    @Override
    public RoadmapBase saveGeneratedRoadmap(RoadmapBase roadmap, Long userId) {
        User user = userService.findById(userId);

        RoadmapBase roadmapToBeSaved = new RoadmapGeneralConcurso();
        roadmapToBeSaved.setUser(user);
        roadmapToBeSaved.setMainGoal(roadmap.getMainGoal());
        roadmapToBeSaved.setDescription(roadmap.getDescription());
        roadmapToBeSaved = roadmapService.save(roadmapToBeSaved, userId);

        roadmap.setUser(user);
        roadmap.setId(roadmapToBeSaved.getId());

        for (AbstractGoal goal : roadmap.getGoals()) {
            goal.setStatus(GoalStatus.ACTIVE);
            GoalBase goalToBeSaved = new GoalBase();
            goalToBeSaved.setRoadmap(roadmapToBeSaved);
            goalToBeSaved.setRoadmapIndex(goal.getRoadmapIndex());
            goalToBeSaved.setDescription(goal.getDescription());
            goalToBeSaved.setName(goal.getName());
            goalToBeSaved.setStatus(goal.getStatus());
            goalToBeSaved = goalService.save(goalToBeSaved, roadmapToBeSaved.getId());

            goal.setRoadmap(roadmapToBeSaved);
            goal.setId(goalToBeSaved.getId());

            for (StudyTopic studyTopic : goal.getStudyTopics()) {
                studyTopic.setGoal(goalToBeSaved);
                studyTopicService.save(studyTopic, goalToBeSaved.getId());
            }
        }

        return roadmapService.save(roadmap, userId);
    }

    @Override
    public RoadmapBase generateAndSaveRoadmap(String mainGoal, String description, Long userId) {
        String aditionalPrompt = roadmapService.getRoadmapPreferencesPromptByUser(userId);

        ResponseGenerateRoadmapDTO generatedRoadmap = generateRoadmap(mainGoal, description, aditionalPrompt, "General");
        AbstractConcurso concurso = new GeneralConcurso();
        //String mainGoal, String description, List<GoalDTO> goals, Long concursoId
        return saveGeneratedRoadmap(
                roadmapMapper.toEntity(
                        new RequestRoadmapDTO(
                                generatedRoadmap.mainGoal(),
                                generatedRoadmap.description(),
                                generatedRoadmap.goals(),
                                concurso.getId()
                        ),
                        concurso,
                        generatedRoadmap.tipo()
                ),
                userId
        );
    }
}
