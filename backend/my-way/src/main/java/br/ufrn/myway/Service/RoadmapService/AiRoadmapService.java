package br.ufrn.myway.Service.RoadmapService;

import br.ufrn.myway.Model.Entities.Roadmap;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiRoadmapService {


    @Autowired
    private RoadmapService roadmapService;

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private ChatModel chatModel;

    public Roadmap createRoadmapWithAi(String mainGoal, String description) {
        String prompt = "Gere um roadmap para o objetivo: {mainGoal}, a descrição desse objetivo é: {description}.";

        return ChatClient.create(chatModel).prompt()
                .user(u -> u.text(prompt)
                        .param(mainGoal, description))
                .call()
                .entity(Roadmap.class);
    }

}
