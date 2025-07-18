//package br.ufrn.myway.Service.RoadmapService.Ai;
//
//import br.ufrn.myway.Model.DTO.Request.RequestRoadmapDTO;
//import br.ufrn.myway.Model.DTO.Response.ResponseGenerateRoadmapDTO;
//import br.ufrn.myway.Model.Entities.Concurso.ConcursoBase;
//import br.ufrn.myway.Model.Entities.Concurso.ConcursoPoliciaCivil;
//import br.ufrn.myway.Model.Entities.Goal.AbstractGoal;
//import br.ufrn.myway.Model.Entities.Goal.DailyGoal;
//import br.ufrn.myway.Model.Entities.Goal.GoalBase;
//import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;
//import br.ufrn.myway.Model.Entities.Roadmap.RoadmapConcursoGeneralista;
//import br.ufrn.myway.Model.Entities.Roadmap.RoadmapConcursoPoliciaCivil;
//import br.ufrn.myway.Model.Entities.StudyTopic;
//import br.ufrn.myway.Model.Entities.User;
//import br.ufrn.myway.Model.Enums.GoalStatus;
//import br.ufrn.myway.Model.Mapper.RoadmapMapper;
//import br.ufrn.myway.Service.GoalService.GoalService;
//import br.ufrn.myway.Service.RoadmapService.RoadmapConcursoGeneralistaService;
//import br.ufrn.myway.Service.StudyTopicService;
//import br.ufrn.myway.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AiRoadmapConcursoPoliciaCivilService implements AbstractAiRoadmapService<RoadmapConcursoPoliciaCivil> {
//
//    @Autowired
//    private RoadmapConcursoGeneralistaService roadmapConcursoGeneralistaService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private GoalService goalService;
//
//    @Autowired
//    private StudyTopicService studyTopicService;
//
//    @Autowired
//    private RoadmapMapper roadmapMapper;
//
//    @Override
//    public RoadmapConcursoPoliciaCivil generateRoadmap(String mainGoal, String description, String aditionalInfo, String tipoConcurso) {
//        return null;
//    }
//
//    @Override
//    public RoadmapConcursoPoliciaCivil saveGeneratedRoadmap(RoadmapConcursoPoliciaCivil roadmap, Long userId) {
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
//            GoalBase goalToBeSaved;
//
//            if (goal instanceof DailyGoal) {
//                goalToBeSaved = new DailyGoal();
//                ((DailyGoal) goalToBeSaved).setResetTime(((DailyGoal) goal).getResetTime());
//            } else {
//                goalToBeSaved = new GoalBase();
//            }
//
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
//    public RoadmapConcursoPoliciaCivil generateAndSaveRoadmap(String mainGoal, String description, Long userId) {
//        String aditionalPrompt = roadmapConcursoGeneralistaService.getRoadmapPreferencesPromptByUser(userId);
//        String promptWithInformations = aditionalPrompt + "\n"
//                + "O concurso é da Policia Civil, logo, pode haver um tipo de Goal do tipo 'DailyGoal' que deve ser criado para exercícios físicos "
//                + "onde o usuário deve realizar atividades físicas diárias para se preparar para o TAF.";
//
//        ResponseGenerateRoadmapDTO generatedRoadmap = generateRoadmap(mainGoal, description, promptWithInformations, "Policia Civil");
//        ConcursoBase concurso = new ConcursoPoliciaCivil();
//        return saveGeneratedRoadmap(
//                roadmapMapper.toEntity(
//                        new RequestRoadmapDTO(
//                                generatedRoadmap.mainGoal(),
//                                generatedRoadmap.description(),
//                                generatedRoadmap.goals(),
//                                concurso.getId()
//                        ),
//                        concurso,
//                        generatedRoadmap.tipo()
//                ),
//                userId
//        );
//    }
//
//}
