//package br.ufrn.myway.Controller.RoadmapController;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.ufrn.myway.Model.DTO.Request.RequestFullRoadmapDTO;
//import br.ufrn.myway.Model.DTO.Request.RequestRoadmapDTO;
//import br.ufrn.myway.Model.DTO.Response.ResponseGenerateRoadmapDTO;
//import br.ufrn.myway.Model.DTO.Response.ResponseRoadmapDTO;
//import br.ufrn.myway.Model.Mapper.RoadmapMapper;
//import br.ufrn.myway.Service.RoadmapService.Ai.AiRoadmapService;
//
//@RestController
//@RequestMapping("/ai/roadmap")
//public class AiRoadmapController {
//
//    @Autowired
//    private AiRoadmapService aiRoadmapService;
//
//    @Autowired
//    private RoadmapMapper roadmapMapper;
//
//    @PostMapping
//    public ResponseEntity<ResponseGenerateRoadmapDTO> generateRoadmap(@RequestBody RequestRoadmapDTO roadmapDTO) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                aiRoadmapService.generateRoadmap(
//                        roadmapDTO.mainGoal(), roadmapDTO.description(), ""
//                )
//        );
//    }
//
//    @PostMapping("/gen-and-save/{userId}")
//    public ResponseEntity<ResponseRoadmapDTO> generateAndSaveRoadmap(@RequestBody RequestFullRoadmapDTO roadmapDTO, @PathVariable Long userId) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                roadmapMapper.toResponse(
//                        aiRoadmapService.generateAndSaveRoadmap(
//                                roadmapDTO.mainGoal(),
//                                roadmapDTO.description(),
//                                userId
//                        )
//                )
//        );
//    }
//
//}
