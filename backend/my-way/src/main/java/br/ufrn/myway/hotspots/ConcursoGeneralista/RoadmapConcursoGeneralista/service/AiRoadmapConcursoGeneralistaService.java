package br.ufrn.myway.hotspots.ConcursoGeneralista.RoadmapConcursoGeneralista.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Service.GoalService.GoalService;
import br.ufrn.myway.coldspots.Roadmap.service.AbstractAiRoadmapService;
import br.ufrn.myway.coldspots.StudyTopics.service.StudyTopicService;
import br.ufrn.myway.coldspots.User.service.UserService; 
import br.ufrn.myway.model.DTO.Request.RequestRoadmapDTO;
import br.ufrn.myway.model.DTO.Response.ResponseGenerateRoadmapDTO;
import br.ufrn.myway.model.entities.Concurso.AbstractConcurso;
import br.ufrn.myway.model.entities.Concurso.ConcursoGeneralista;
import br.ufrn.myway.model.entities.Goal.AbstractGoal;
import br.ufrn.myway.model.entities.Goal.GoalBase; 
import br.ufrn.myway.model.entities.StudyTopic;
import br.ufrn.myway.model.entities.User;
import br.ufrn.myway.model.entities.roadmap.RoadmapConcursoGeneralista;
import br.ufrn.myway.model.enums.GoalStatus;
import br.ufrn.myway.model.mapper.RoadmapMapper;

@Service
public class AiRoadmapConcursoGeneralistaService implements AbstractAiRoadmapService<RoadmapConcursoGeneralista, ResponseGenerateRoadmapDTO> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoadmapConcursoGeneralistaService roadmapConcursoGeneralistaService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private StudyTopicService studyTopicService;

    @Autowired
    private RoadmapMapper roadmapMapper;

    @Autowired
    private ChatModel chatModel;

    @Override
    public ResponseGenerateRoadmapDTO generateRoadmap(String mainGoal, String description, String aditionalInfo, String tipoConcurso) {
        String prompt = "Gere um roadmap de estudos para o concurso {tipoConcurso}: {mainGoal}, a descrição desse objetivo é: {description}.";
        final String fullPrompt = prompt.concat(aditionalInfo);
        return ChatClient.create(chatModel).prompt()
                .user(u -> u.text(fullPrompt)
                .param("mainGoal", mainGoal)
                .param("description", description).param("tipoConcurso", tipoConcurso))
                .call()
                .entity(ResponseGenerateRoadmapDTO.class);
    }

    @Override
    public RoadmapConcursoGeneralista saveGeneratedRoadmap(RoadmapConcursoGeneralista roadmap, Long userId) {
        User user = userService.findById(userId);

        RoadmapConcursoGeneralista roadmapToBeSaved = new RoadmapConcursoGeneralista();
        roadmapToBeSaved.setUser(user);
        roadmapToBeSaved.setMainGoal(roadmap.getMainGoal());
        roadmapToBeSaved.setDescription(roadmap.getDescription());
        roadmapToBeSaved = roadmapConcursoGeneralistaService.save(roadmapToBeSaved, userId);

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

        return roadmapConcursoGeneralistaService.save(roadmap, userId);
    }

    @Override
    public RoadmapConcursoGeneralista generateAndSaveRoadmap(String mainGoal, String description, Long userId) {
        String aditionalPrompt = roadmapConcursoGeneralistaService.getRoadmapPreferencesPromptByUser(userId);

        ResponseGenerateRoadmapDTO generatedRoadmap = generateRoadmap(mainGoal, description, aditionalPrompt, "General");
        AbstractConcurso concurso = new ConcursoGeneralista();

        return saveGeneratedRoadmap(
                roadmapMapper.toRoadmapConcursoGeneralistaEntity(
                        new RequestRoadmapDTO(
                                generatedRoadmap.mainGoal(),
                                generatedRoadmap.description(),
                                generatedRoadmap.goals(),
                                concurso.getId()
                        )
                ),
                userId
        ); 
    }

}
