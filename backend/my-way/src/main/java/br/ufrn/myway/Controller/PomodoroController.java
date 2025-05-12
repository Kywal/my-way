package Controller;

import Entities.PomodoroClass;
import Service.PomodoroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pomodoro")
public class PomodoroController {
    @Autowired
    private PomodoroService pomodoroService;

    @PostMapping("/start-study")
    public PomodoroClass startStudySession(@RequestParam Long timerId){
        return pomodoroService.startStudySession(timerId);
    }
    @PostMapping("/start-break")
    public PomodoroClass startBreakSession(@RequestParam Long timerId, @RequestParam boolean isLongBreak){
        return pomodoroService.starBreakSession(timerId, isLongBreak);
    }
    @PostMapping ("/stop")
    public PomodoroClass stopTimer(@RequestParam long timerId){
        return pomodoroService.stopTimer(timerId);
    }
    @GetMapping
    public PomodoroClass getPomodoroTimerStatus(@RequestParam Long timerId){
        return pomodoroService.getRemainingTime(timerId);
    }
}
