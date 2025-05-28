package br.ufrn.myway.Service.RoadmapService;

import br.ufrn.myway.Model.DTO.Response.ResponseGenerateRoadmapDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.Roadmap;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Model.Mapper.RoadmapMapper;
import br.ufrn.myway.Service.BusinessException;
import br.ufrn.myway.Service.GoalService;
import br.ufrn.myway.Service.StudyTopicService;
import br.ufrn.myway.Service.UserService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AiRoadmapService {

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

    @Autowired
    private ChatModel chatModel;

    public ResponseGenerateRoadmapDTO generateRoadmap(String mainGoal, String description) {
        String prompt = "Gere um roadmap para o objetivo: {mainGoal}, a descrição desse objetivo é: {description}.";

        return ChatClient.create(chatModel).prompt()
                .user(u -> u.text(prompt)
                        .param("mainGoal", mainGoal)
                        .param("description", description))
                .call()
                .entity(ResponseGenerateRoadmapDTO.class);
    }

    public Roadmap saveGeneratedRoadmap(Roadmap roadmap, Long userId) {
        User user = userService.findById(userId);

        if (user == null)
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("User"));

        Roadmap roadmapToBeSaved = new Roadmap();
        roadmapToBeSaved.setUser(user);
        roadmapToBeSaved.setMainGoal(roadmap.getMainGoal());
        roadmapToBeSaved.setDescription(roadmap.getDescription());
        roadmapToBeSaved = roadmapService.save(roadmapToBeSaved, userId);

        roadmap.setUser(user);
        roadmap.setId(roadmapToBeSaved.getId());

        for (Goal goal : roadmap.getGoals()) {

            Goal goalToBeSaved = new Goal();
            goalToBeSaved.setRoadmap(roadmapToBeSaved);
            goalToBeSaved.setRoadmapIndex(goal.getRoadmapIndex());
            goalToBeSaved.setDescription(goal.getDescription());
            goalToBeSaved.setName(goal.getName());
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

    public Roadmap generateAndSaveRoadmap(String mainGoal, String description, Long userId) {
        ResponseGenerateRoadmapDTO generatedRoadmap = generateRoadmap(mainGoal, description);

        return saveGeneratedRoadmap(
                roadmapMapper.toEntity(generatedRoadmap), userId
        );
    }

}
