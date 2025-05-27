package br.ufrn.myway.Service.RoadmapService;

import br.ufrn.myway.Model.DTO.Response.ResponseGenerateRoadmapDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.Roadmap;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Model.Enums.RoadMapStatus;
import br.ufrn.myway.Service.BusinessException;
import br.ufrn.myway.Service.GoalService;
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
        Roadmap firstRoadmapToBeSaved = new Roadmap();

        if (user != null) {
            firstRoadmapToBeSaved.setUser(roadmap.getUser());
        } else throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("User"));

        firstRoadmapToBeSaved.setMainGoal(roadmap.getMainGoal());
        firstRoadmapToBeSaved.setDescription(roadmap.getDescription());
        firstRoadmapToBeSaved.setStatus(RoadMapStatus.ACTIVE);

        firstRoadmapToBeSaved = roadmapService.save(firstRoadmapToBeSaved, userId);
        roadmap.setId(firstRoadmapToBeSaved.getId());

        for (Goal goal : roadmap.getGoals()) {

            Goal firstGoalToBeSaved = new Goal();
            firstGoalToBeSaved.setRoadmap(roadmap);
            firstGoalToBeSaved.setRoadmapIndex(goal.getRoadmapIndex());
            firstGoalToBeSaved.setDescription(goal.getDescription());
            firstGoalToBeSaved.setName(goal.getName());

            firstGoalToBeSaved = goalService.save(firstGoalToBeSaved, roadmap.getId());
            goal.setId(firstGoalToBeSaved.getId());

            for (StudyTopic studyTopic : goal.getStudyTopics()) {
                studyTopic.setGoal(goal);
            }
        }

        return roadmapService.save(roadmap, userId);
    }

}
