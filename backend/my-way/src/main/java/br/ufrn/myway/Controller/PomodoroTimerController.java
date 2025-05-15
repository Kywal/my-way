package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.DTO.PomodoroTimerDTO;
import br.ufrn.myway.Model.Entities.PomodoroTimer;
import br.ufrn.myway.Model.Mapper.PomodoroTimerMapper;
import br.ufrn.myway.Service.PomodoroTimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pomodoro")
public class PomodoroTimerController {
    @Autowired
    private PomodoroTimerService pomodoroService;
    @Autowired
    private PomodoroTimerMapper pomodoroTimerMapper;

    @PostMapping("/start-study")
    public ResponseEntity<PomodoroTimerDTO> startStudySession(@RequestBody PomodoroTimerDTO pomodoroTimerDTO) {
        PomodoroTimer pomodoro = pomodoroTimerMapper.toEntity(pomodoroTimerDTO);
        pomodoro = pomodoroService.startStudySession(pomodoro);
        return new ResponseEntity<>(pomodoroTimerMapper.toDto(pomodoro), HttpStatus.CREATED);
    }
}
