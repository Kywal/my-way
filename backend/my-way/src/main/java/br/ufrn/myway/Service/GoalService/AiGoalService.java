package br.ufrn.myway.Service.GoalService;

import br.ufrn.myway.Model.DTO.Response.ResponseGenerateGoalDTO;
import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.Roadmap;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Model.Mapper.GoalMapper;
import br.ufrn.myway.Service.BusinessException;
import br.ufrn.myway.Service.RoadmapService.RoadmapService;
import br.ufrn.myway.Service.StudyTopicService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AiGoalService {

    @Autowired
    private RoadmapService roadmapService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private GoalMapper goalMapper;

    @Autowired
    private ChatModel chatModel;
    @Autowired
    private StudyTopicService studyTopicService;

    public ResponseGenerateGoalDTO generateGoal(Roadmap roadmap){
        String prompt =
                """
                Gere um novo último objetivo para o seguinte roadmap: \n{roadmap}
                """;

        return ChatClient.create(chatModel).prompt()
                .user(u ->
                        u.text(prompt).param("roadmap", roadmap.toString())
                )
                .call()
                .entity(ResponseGenerateGoalDTO.class);
    }

    public Goal saveGenerated(Roadmap roadmap, Goal goal) {
        Goal goalToBeSaved = new Goal();
        goalToBeSaved.setDescription(goal.getDescription());
        goalToBeSaved.setName(goal.getName());
        goalToBeSaved.setRoadmap(roadmap);
        goalToBeSaved = goalService.save(goalToBeSaved, roadmap.getId());

        goal.setRoadmap(roadmap);
        goal.setId(goalToBeSaved.getId());
        goal.setRoadmapIndex(goalToBeSaved.getRoadmapIndex());

        for (StudyTopic studyTopic : goal.getStudyTopics()) {
            studyTopic.setGoal(goalToBeSaved);
            studyTopicService.save(studyTopic, goalToBeSaved.getId());
        }

        return goalService.save(goal, roadmap.getId());
    }

    public Goal generateAndSave(Long roadmapId) {

        Roadmap roadmap = roadmapService.findById(roadmapId);
        if (roadmap == null) throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"));

        ResponseGenerateGoalDTO generatedGoal = generateGoal(roadmap);

        return saveGenerated(
                roadmap,
                goalMapper.toEntity(generatedGoal)
        );
    }


}
