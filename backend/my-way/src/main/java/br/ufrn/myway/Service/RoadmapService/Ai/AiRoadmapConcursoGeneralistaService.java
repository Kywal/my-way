//package br.ufrn.myway.Service.RoadmapService.Ai;
//
//import br.ufrn.myway.Model.Entities.Goal.AbstractGoal;
//import br.ufrn.myway.Model.Entities.Goal.GoalBase;
//import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;
//import br.ufrn.myway.Model.Entities.Roadmap.RoadmapConcursoGeneralista;
//import br.ufrn.myway.Model.Entities.StudyTopic;
//import br.ufrn.myway.Model.Entities.User;
//import br.ufrn.myway.Model.Enums.GoalStatus;
//import br.ufrn.myway.Service.GoalService.GoalService;
//import br.ufrn.myway.Service.RoadmapService.RoadmapConcursoGeneralistaService;
//import br.ufrn.myway.Service.StudyTopicService;
//import br.ufrn.myway.Service.UserService;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.chat.model.ChatModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AiRoadmapConcursoGeneralistaService implements AbstractAiRoadmapService<RoadmapConcursoGeneralista> {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoadmapConcursoGeneralistaService roadmapConcursoGeneralistaService;
//
//    @Autowired
//    private GoalService goalService;
//
//    @Autowired
//    private StudyTopicService studyTopicService;
//
//    @Autowired
//    private ChatModel chatModel;
//
//    @Override
//    public RoadmapConcursoGeneralista generateRoadmap(String mainGoal, String description, String aditionalInfo, String tipoConcurso) {
//        String prompt = "Gere um roadmap de estudos para o concurso {tipoConcurso}: {mainGoal}, a descrição desse objetivo é: {description}.";
//        final String fullPrompt = prompt.concat(aditionalInfo);
//        return ChatClient.create(chatModel).prompt()
//                .user(u -> u.text(fullPrompt)
//                .param("mainGoal", mainGoal)
//                .param("description", description).param("tipoConcurso", tipoConcurso))
//                .call()
//                .entity(RoadmapConcursoGeneralista.class);
//    }
//
//    @Override
//    public RoadmapConcursoGeneralista saveGeneratedRoadmap(RoadmapConcursoGeneralista roadmap, Long userId) {
//        User user = userService.findById(userId);
//
//        RoadmapBase roadmapToBeSaved = new RoadmapConcursoGeneralista();
//        roadmapToBeSaved.setUser(user);
//        roadmapToBeSaved.setMainGoal(roadmap.getMainGoal());
//        roadmapToBeSaved.setDescription(roadmap.getDescription());
//        roadmapToBeSaved = roadmapConcursoGeneralistaService.save(roadmapToBeSaved, userId);
//
//        roadmap.setUser(user);
//        roadmap.setId(roadmapToBeSaved.getId());
//
//        for (AbstractGoal goal : roadmap.getGoals()) {
//            goal.setStatus(GoalStatus.ACTIVE);
//            GoalBase goalToBeSaved = new GoalBase();
//            goalToBeSaved.setRoadmap(roadmapToBeSaved);
//            goalToBeSaved.setRoadmapIndex(goal.getRoadmapIndex());
//            goalToBeSaved.setDescription(goal.getDescription());
//            goalToBeSaved.setName(goal.getName());
//            goalToBeSaved.setStatus(goal.getStatus());
//            goalToBeSaved = goalService.save(goalToBeSaved, roadmapToBeSaved.getId());
//
//            goal.setRoadmap(roadmapToBeSaved);
//            goal.setId(goalToBeSaved.getId());
//
//            for (StudyTopic studyTopic : goal.getStudyTopics()) {
//                studyTopic.setGoal(goalToBeSaved);
//                studyTopicService.save(studyTopic, goalToBeSaved.getId());
//            }
//        }
//
//        return roadmapConcursoGeneralistaService.save(roadmap, userId);
//    }
//
//    @Override
//    public RoadmapConcursoGeneralista generateAndSaveRoadmap(String mainGoal, String description, Long userId){
////        String aditionalPrompt = roadmapService.getRoadmapPreferencesPromptByUser(userId);
////
////        ResponseGenerateRoadmapDTO generatedRoadmap = generateRoadmap(mainGoal, description, aditionalPrompt, "General");
////        AbstractConcurso concurso = new ConcursoGeneralista();
////        //String mainGoal, String description, List<GoalDTO> goals, Long concursoId
////        return saveGeneratedRoadmap(
////                roadmapMapper.toEntity(
////                        new RequestRoadmapDTO(
////                                generatedRoadmap.mainGoal(),
////                                generatedRoadmap.description(),
////                                generatedRoadmap.goals(),
////                                concurso.getId()
////                        ),
////                        concurso,
////                        generatedRoadmap.tipo()
////                ),
////                userId
////        );
//        return null;
//    }
//
//}
