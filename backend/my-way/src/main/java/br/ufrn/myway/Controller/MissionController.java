package br.ufrn.myway.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.myway.Model.DTO.MissionDTO;
import br.ufrn.myway.Model.Entities.Mission; 
import br.ufrn.myway.Model.Enums.MissionFrequency;
import br.ufrn.myway.Model.Enums.MissionType;
import br.ufrn.myway.Model.Mapper.MissionMapper;
import br.ufrn.myway.Service.MissionService;

@RestController
@RequestMapping("/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @Autowired
    private MissionMapper missionMapper;

    @GetMapping
    public List<Mission> listarTodas() {
        return missionService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> buscarPorId(@PathVariable Long id) {
        Mission mission = missionService.findById(id);
        return ResponseEntity.ok(mission);
    }

    @PostMapping
    public ResponseEntity<Mission> criar(@RequestBody Mission mission) {
        Mission salva = missionService.save(mission);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PostMapping("/daily/quantity-time")
    public ResponseEntity<MissionDTO> criarDailyQuantityMission(@RequestBody MissionDTO mission) {
        Mission quantityTimeMission = missionMapper.toEntity(mission);
        quantityTimeMission.setStartDate(LocalDateTime.now());
        quantityTimeMission.setEndDate(LocalDateTime.now());
        quantityTimeMission.setFrequency(MissionFrequency.DAILY);
        quantityTimeMission.setType(MissionType.QUANTITY_TIME);

        quantityTimeMission.setQuantityGoal(null);

        quantityTimeMission = missionService.save(quantityTimeMission);
        return ResponseEntity.status(HttpStatus.CREATED).body(missionMapper.toDto(quantityTimeMission));
    }

     @PostMapping("/weekly/quantity-goal")
     public ResponseEntity<MissionDTO> criarWeeklyQuantityGoal(@RequestBody MissionDTO mission) {
         Mission quantityGoalMission = missionMapper.toEntity(mission);
         quantityGoalMission.setStartDate(LocalDateTime.now());
         quantityGoalMission.setEndDate(LocalDateTime.now());
         quantityGoalMission.setFrequency(MissionFrequency.WEEKLY);
         quantityGoalMission.setType(MissionType.QUANTITY_GOAL);

         quantityGoalMission.setTimeInMinutes(null);

         quantityGoalMission = missionService.save(quantityGoalMission);
         return ResponseEntity.status(HttpStatus.CREATED).body(missionMapper.toDto(quantityGoalMission));
     }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(Map.of("msg", "funcionando"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        missionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
