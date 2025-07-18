package br.ufrn.myway.coldspots.Mission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.myway.model.DTO.FullUserMissionDTO;
import br.ufrn.myway.model.DTO.MissionDTO;
import br.ufrn.myway.model.DTO.MissionProgressDTO;
import br.ufrn.myway.model.entities.Mission;
import br.ufrn.myway.model.entities.User;
import br.ufrn.myway.model.entities.UserMission;
import br.ufrn.myway.model.enums.MissionFrequency;
import br.ufrn.myway.model.enums.MissionType;
import br.ufrn.myway.model.mapper.FullUserMissionMapper;
import br.ufrn.myway.model.mapper.MissionMapper;
import br.ufrn.myway.coldspots.Mission.service.MissionService;
import br.ufrn.myway.coldspots.Mission.service.UserMissionService;
import br.ufrn.myway.coldspots.User.service.UserService;

@RestController
@RequestMapping("/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @Autowired
    private MissionMapper missionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMissionService userMissionService;

    @Autowired
    private FullUserMissionMapper fullUserMissionMapper;

    @GetMapping
    public List<Mission> listarTodas() {
        return missionService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> buscarPorId(@PathVariable Long id) {
        Mission mission = missionService.findById(id);
        return ResponseEntity.ok(mission);
    }

    @GetMapping("/daily-mission")
    public ResponseEntity<FullUserMissionDTO> getCurrentDailyMission(@RequestParam String emailUser) {
        UserMission userMission = userMissionService.findByUserAndDateRange(emailUser);
        return ResponseEntity.ok(fullUserMissionMapper.toDto(userMission));
    }

    @PostMapping
    public ResponseEntity<Mission> criar(@RequestBody Mission mission) {
        Mission salva = missionService.save(mission);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PostMapping("/daily/quantity-time")
    public ResponseEntity<MissionDTO> criarDailyQuantityMission(@RequestBody MissionDTO mission) {
        Mission quantityTimeMission = missionMapper.toEntity(mission);
        quantityTimeMission.setFrequency(MissionFrequency.DAILY);
        quantityTimeMission.setType(MissionType.QUANTITY_TIME);

        quantityTimeMission.setQuantityGoal(null);

        quantityTimeMission = missionService.save(quantityTimeMission);
        return ResponseEntity.status(HttpStatus.CREATED).body(missionMapper.toDto(quantityTimeMission));
    }

    @PostMapping("/weekly/quantity-goal")
    public ResponseEntity<MissionDTO> criarWeeklyQuantityGoal(@RequestBody MissionDTO mission) {
        Mission quantityGoalMission = missionMapper.toEntity(mission);
        quantityGoalMission.setFrequency(MissionFrequency.WEEKLY);
        quantityGoalMission.setType(MissionType.QUANTITY_GOAL);

        quantityGoalMission.setTimeInMinutes(null);

        quantityGoalMission = missionService.save(quantityGoalMission);
        return ResponseEntity.status(HttpStatus.CREATED).body(missionMapper.toDto(quantityGoalMission));
    }

    @PostMapping("/daily/check-quantity-time")
    public ResponseEntity<?> checkQuantityTimeMission(@RequestBody MissionProgressDTO missionProgress) {
        User user = userService.findById(missionProgress.userId());
        boolean concluded = userMissionService.verifyConcludedQuantityTimeMission(user, missionProgress.timeInMinutes());
        return ResponseEntity.ok(concluded ? "Missão concluída" : "Missão em andamento");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        missionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
