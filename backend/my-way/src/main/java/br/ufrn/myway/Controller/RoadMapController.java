package br.ufrn.myway.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.myway.Model.DTO.RoadMapDTO;
import br.ufrn.myway.Model.Entities.RoadMap;
import br.ufrn.myway.Model.Mapper.RoadMapMapper;
import br.ufrn.myway.Service.RoadMapService;

@RestController
@RequestMapping("/roadmap")
public class RoadMapController {

    @Autowired
    private RoadMapService roadMapService;

    @Autowired
    private RoadMapMapper roadMapMapper;

    @PostMapping("/save/{idUser}")
    public ResponseEntity<RoadMapDTO> save(@RequestBody RoadMapDTO roadMapDto, @PathVariable Long idUser){
        RoadMap roadMap = roadMapMapper.toEntity(roadMapDto);
        return ResponseEntity.ok(roadMapMapper.toDto(roadMapService.save(roadMap, idUser)));
    }
    @GetMapping("/list")
    public List<RoadMapDTO> list(){
        return roadMapMapper.toListDTO(roadMapService.list());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoadMapDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(roadMapMapper.toDto(roadMapService.findById(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        roadMapService.deletar(id);
        return ResponseEntity.ok("RoadMap successfully deleted.");
    }
}
