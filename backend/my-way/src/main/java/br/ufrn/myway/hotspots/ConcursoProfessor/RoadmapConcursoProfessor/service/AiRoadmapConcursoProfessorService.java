package br.ufrn.myway.hotspots.ConcursoProfessor.RoadmapConcursoProfessor.service;

import br.ufrn.myway.coldspots.model.DTO.Request.RequestRoadmapDTO;
import br.ufrn.myway.coldspots.model.DTO.Response.ResponseGenerateRoadmapDTO;
import br.ufrn.myway.coldspots.model.entities.Concurso.ConcursoBase;
import br.ufrn.myway.coldspots.model.entities.Concurso.ConcursoPoliciaCivil;
import br.ufrn.myway.coldspots.model.entities.Goal.AbstractGoal;
import br.ufrn.myway.coldspots.model.entities.Goal.GoalBase;
import br.ufrn.myway.coldspots.model.entities.StudyTopic;
import br.ufrn.myway.coldspots.model.entities.User;
import br.ufrn.myway.coldspots.model.entities.roadmap.RoadmapConcursoProfessor;
import br.ufrn.myway.coldspots.model.enums.GoalStatus;
import br.ufrn.myway.coldspots.model.mapper.RoadmapMapper;
import br.ufrn.myway.hotspots.Goal.service.GoalService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.myway.coldspots.Roadmap.service.AbstractAiRoadmapService;
import br.ufrn.myway.coldspots.StudyTopics.service.StudyTopicService;
import br.ufrn.myway.coldspots.User.service.UserService;

@Service
public class AiRoadmapConcursoProfessorService implements AbstractAiRoadmapService<RoadmapConcursoProfessor, ResponseGenerateRoadmapDTO> {

    @Autowired
    private RoadmapConcursoProfessorService roadmapConcursoProfessorService;

    @Autowired
    private UserService userService;

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
    public RoadmapConcursoProfessor saveGeneratedRoadmap(RoadmapConcursoProfessor roadmap, Long userId) {
        User user = userService.findById(userId);

        RoadmapConcursoProfessor roadmapToBeSaved = new RoadmapConcursoProfessor();
        roadmapToBeSaved.setUser(user);
        roadmapToBeSaved.setMainGoal(roadmap.getMainGoal());
        roadmapToBeSaved.setDescription(roadmap.getDescription());
        roadmapToBeSaved = roadmapConcursoProfessorService.save(roadmapToBeSaved, userId);

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

        return roadmapConcursoProfessorService.save(roadmap, userId);
    }

    @Override
    public RoadmapConcursoProfessor generateAndSaveRoadmap(String mainGoal, String description, Long userId) {
        String aditionalPrompt = roadmapConcursoProfessorService.getRoadmapPreferencesPromptByUser(userId);
        String promptWithInformations = aditionalPrompt + "\n"
                + "O concurso é da Policia Civil, logo, pode haver um tipo de Goal do tipo 'DailyGoal' que deve ser criado para exercícios físicos "
                + "onde o usuário deve realizar atividades físicas diárias para se preparar para o TAF.";

        ResponseGenerateRoadmapDTO generatedRoadmap = generateRoadmap(mainGoal, description, promptWithInformations, "Policia Civil");
        ConcursoBase concurso = new ConcursoPoliciaCivil();
        return saveGeneratedRoadmap(
                roadmapMapper.toRoadmapConcursoProfessorEntity(
                        new RequestRoadmapDTO(
                                generatedRoadmap.mainGoal(),
                                generatedRoadmap.description(),
                                generatedRoadmap.goals(),
                                concurso.getId()
                        )
                ), userId
        );
    }
}
