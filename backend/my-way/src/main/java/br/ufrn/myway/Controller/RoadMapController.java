package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.DTO.RoadMapDTO;
import br.ufrn.myway.Model.Entities.RoadMap;
import br.ufrn.myway.Model.Mapper.RoadMapMapper;
import br.ufrn.myway.Service.RoadMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/roadmap")
public class RoadMapController {

    @Autowired
    RoadMapService roadMapService;

    @Autowired
    private RoadMapMapper roadMapMapper;

    @PostMapping("/save/{id}")
    public ResponseEntity<RoadMapDTO> save(@RequestBody RoadMapDTO roadMapDto, @PathVariable Long id){
        RoadMap roadMap = roadMapMapper.toEntity(roadMapDto);
        return ResponseEntity.ok(roadMapMapper.toDto(roadMapService.save(roadMap, id)));
    }
    @GetMapping("/list")
    public List<RoadMapDTO> list(){
        return roadMapMapper.toListDTO(roadMapService.list());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoadMapDTO> get(@PathVariable long id){
        return ResponseEntity.ok(roadMapMapper.toDto(roadMapService.findById(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        roadMapService.deletar(id);
        return ResponseEntity.ok("RoadMap successfully deleted.");
    }
}
