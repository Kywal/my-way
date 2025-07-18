package br.ufrn.myway.Controller.RoadmapController;

import br.ufrn.myway.model.DTO.Request.RequestFullRoadmapDTO;
import br.ufrn.myway.model.DTO.Request.RequestRoadmapDTO;
import br.ufrn.myway.model.DTO.Response.ResponseRoadmapDTO;
import br.ufrn.myway.model.enums.RoadmapStatus;
import br.ufrn.myway.model.mapper.RoadmapMapper;
import br.ufrn.myway.Service.RoadmapService.RoadmapConcursoGeneralistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Profile("Generalista")
@RestController
@RequestMapping("/roadmap")
public class RoadmapConcursoGeneralistaController implements AbstractRoadmapController<
        RequestRoadmapDTO,
        RequestFullRoadmapDTO,
        ResponseRoadmapDTO
> {

    @Autowired
    private RoadmapConcursoGeneralistaService roadmapService;

    @Autowired
    private RoadmapMapper roadmapMapper;

    @Override
    @PostMapping("/{userId}")
    public ResponseEntity<ResponseRoadmapDTO> create(@RequestBody RequestRoadmapDTO roadmapDTO, @PathVariable Long userId) {
        return ResponseEntity.ok(
                roadmapMapper.toResponse(
                        roadmapService.save(
                                roadmapMapper.toEntity(roadmapDTO), userId
                        )
                )
        );
    }

    @Override
    @PostMapping("/save/{userId}")
    public ResponseEntity<ResponseRoadmapDTO> save(@RequestBody RequestFullRoadmapDTO roadmapDTO, @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                roadmapMapper.toResponse(
                        roadmapService.save(
                                roadmapMapper.toEntity(roadmapDTO), userId
                        )
                )
        );
    }

    @Override
    @GetMapping
    public List<ResponseRoadmapDTO> findAll() {
        return roadmapService.findAll()
                .stream()
                .map(
                        roadmap -> roadmapMapper.toResponse(roadmap)
                )
                .toList();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseRoadmapDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                roadmapMapper.toResponse(
                        roadmapService.findById(id)
                )
        );
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        roadmapService.remove(id);
        return ResponseEntity.ok("Roadmap successfully deleted.");
    }

    @Override
    @GetMapping("/find-by-user/{userId}")
    public List<ResponseRoadmapDTO> findByUser(@PathVariable Long userId) {
        return roadmapService.findRoadmapByUser(userId).stream()
                .map(
                        roadmap -> roadmapMapper.toResponse(roadmap)
                )
                .toList();
    }

    @Override
    @PostMapping("/finish-roadmap/{id}")
    public ResponseEntity<ResponseRoadmapDTO> finish(@PathVariable Long id) {
        return ResponseEntity.ok(
                roadmapMapper.toResponse(
                        roadmapService.finishRoadmap(id)
                )
        );
    }

    @Override
    @PostMapping("/cancel-roadmap/{id}")
    public ResponseEntity<ResponseRoadmapDTO> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(
                roadmapMapper.toResponse(
                        roadmapService.cancelRoadmap(id)
                )
        );
    }

    @Override
    @GetMapping("/find-active/{userId}")
    public ResponseEntity<ResponseRoadmapDTO> findActiveRoadmap(@PathVariable Long userId) {
        return ResponseEntity.ok(
                roadmapMapper.toResponse(
                        roadmapService.getByStatus(
                                userId,
                                RoadmapStatus.ACTIVE
                        )
                )
        );
    }

}
