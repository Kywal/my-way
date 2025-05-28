package br.ufrn.myway.Service.GoalService;

import br.ufrn.myway.Model.DTO.Response.ResponseGenerateGoalDTO;
import br.ufrn.myway.Model.Entities.Roadmap;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Service.BusinessException;
import br.ufrn.myway.Service.RoadmapService.RoadmapService;
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
    private ChatModel chatModel;

    public ResponseGenerateGoalDTO generateGoal(Long roadmapId, Long roadmapIndex){

        Roadmap roadmap = roadmapService.findById(roadmapId);
        if (roadmap == null) throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"));

        String prompt =
                """
                Gere um objetivo para o seguinte roadmap: \n{roadmap}
                \n
                Leve em conta que a posição do objetivo dentro do roadmap é a posição {roadmapIndex}.
                \n
                Quando já houver um objetivo na mesma posição,
                adicione temas intermediários entre o objetivo de mesma posição e o próximo objetivo ou
                aprofunde o tema do objetivo anterior. Sempre leve em conta o roadmap como um todo.
               """;

        return ChatClient.create(chatModel).prompt()
                .user(u -> u.text(prompt)
                        .param("roadmap", roadmap.toString())
                        .param("roadmapIndex", roadmapIndex))
                .call()
                .entity(ResponseGenerateGoalDTO.class);
    }


}
