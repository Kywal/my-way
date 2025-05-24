package br.ufrn.myway.Controller;

import java.util.List;

import br.ufrn.myway.Model.DTO.Response.ResponseRoadmapDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.myway.Model.DTO.Request.RequestRoadmapDTO;
import br.ufrn.myway.Model.Mapper.RoadMapMapper;
import br.ufrn.myway.Service.RoadMapService;

@RestController
@RequestMapping("/roadmap")
public class RoadMapController {

    @Autowired
    private RoadMapService roadmapService;

    @Autowired
    private RoadMapMapper roadmapMapper;

    @PostMapping("/save/{userId}")
    public ResponseEntity<ResponseRoadmapDTO> save(@RequestBody RequestRoadmapDTO roadmapDTO, @PathVariable Long userId){
        return ResponseEntity.ok(
                roadmapMapper.toResponse(
                        roadmapService.save(
                                roadmapMapper.toEntity(roadmapDTO), userId
                        )
                )
        );
    }

    @GetMapping("/list")
    public List<ResponseRoadmapDTO> list(){
        return roadmapMapper.toListDTO(roadmapService.list());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseRoadmapDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(
                roadmapMapper.toResponse(
                        roadmapService.findById(id)
                )
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        roadmapService.deletar(id);
        return ResponseEntity.ok("RoadMap successfully deleted.");
    }
}
