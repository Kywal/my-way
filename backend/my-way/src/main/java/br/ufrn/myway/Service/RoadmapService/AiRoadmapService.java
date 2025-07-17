package br.ufrn.myway.Service.RoadmapService;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufrn.myway.Model.DTO.Response.ResponseGenerateRoadmapDTO;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;

public abstract class AiRoadmapService implements AbstractAiRoadmapService {
   
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
    public RoadmapBase saveGeneratedRoadmap(RoadmapBase roadmap, Long userId) {
        return null;
    }

    @Override
    public RoadmapBase generateAndSaveRoadmap(String mainGoal, String description, Long userId) { 
        return null;
    }
    
}
